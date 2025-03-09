package com.perth.project.Login.User.UserFuntions.DownloadImplemetation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Login.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckAut {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public String CheckToken(String token,String name) {
        if (token == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Token no encontrado"
            );
        }
        String username = jwtService.getUsernameFromToken(token);
        UserDetails user = userRepository.findByUsername(username).orElse(null);
        String authority = user.getAuthorities().stream()
            .map(grantedAuthority -> grantedAuthority.getAuthority())
            .findFirst()
            .orElse(null);
        System.out.println(name + username);
        if(name.equals(username) ){
            return name;
        }else{
            if (authority.equals("ADMIN")) {
                return name;
            } else {
                throw new BusinessException(
                        BusinessErrorCodes.BAD_REGISTER,
                        "No tienes permisos para descargar este archivo"
                );
            }
        }
    }
}
