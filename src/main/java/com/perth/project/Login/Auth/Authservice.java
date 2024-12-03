package com.perth.project.Login.Auth;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.perth.project.Login.User.*;
import com.perth.project.Login.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Authservice {
    final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserFuntions userFuntions;
    @Autowired
    @Qualifier("emailSession")
    private final Session emailSession;

    public AuthResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (!userRepository.isAccountBlocked(request.getUsername())) {
                UserDetails user = userRepository.findByUsername(request.getUsername()).orElse(null);

                String token = jwtService.getToken(user);

                return AuthResponse.builder()
                        .response(token)
                        .build();
            } else {
                return AuthResponse.builder()
                        .response("Cuenta bloqueda")
                        .build();

            }
        } catch (Exception e) {
            UserDetails user = userRepository.findByUsername(request.getUsername()).orElse(null);
            if (user != null) {

                userRepository.incrementFailedTrys(request.getUsername());
                Integer failedtrys = userRepository.getFailedTrys(request.username);
                if (failedtrys == 3) {
                    userRepository.blockAccount(request.getUsername());
                    return AuthResponse.builder()
                            .response("Demasiados intentos, cuenta bloqueada.")
                            .build();
                }
            }
            return AuthResponse.builder()
                    .response("Credenciales no validas")
                    .build();
        }
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .identification(request.getIdentification())
                .profile(request.getProfile())
                .area(request.getArea())
                .failedAttemps(0)
                .blockedAccount(false)
                .role(Role.USER)
                .build();

        AuthResponse validationResponse = userFuntions.Validation(user);
        if (validationResponse != null) {
            return validationResponse;
        }
        userRepository.save(user);
        String templatePath = "src/main/resources/templates/Email.html";

        UserFuntions.Notification(request.getEmail(), templatePath, user.getUsername(), emailSession);
        return AuthResponse.builder()
                .response(jwtService.getToken(user))
                .build();
    }
}
