package com.perth.project.Login.jwt;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = GetTokenFromRequest(request);
        final String userName;
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        userName = jwtService.getUsernameFromToken(token);

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails((new WebAuthenticationDetailsSource().buildDetails(request)));
                SecurityContextHolder.getContext().setAuthentication((authToken));
            }
        }
        filterChain.doFilter(request, response);
    }

    private String GetTokenFromRequest(HttpServletRequest request) {
        final String AuthHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotEmpty(AuthHeader) && AuthHeader.startsWith("Bearer ")) {
            return AuthHeader.substring(7);
        }
        return null;
    }

}
