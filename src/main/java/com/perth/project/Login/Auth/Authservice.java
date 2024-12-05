package com.perth.project.Login.Auth;

import java.io.IOException;
import java.nio.file.Files;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
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
        String password = UserFuntions.generatePassword();
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(password))
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
        String templatePath = "";
        try {
            ClassPathResource resource = new ClassPathResource("templates/Email.html");
            Path tempFile = Files.createTempFile("email-template", ".html");
            Files.copy(resource.getInputStream(), tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            templatePath = tempFile.toAbsolutePath().toString();
        } catch (IOException e) {
            System.err.println("Error al obtener el recurso del classpath: " + e.getMessage());
        }
        UserFuntions.Notification(request.getEmail(), templatePath, user.getUsername(), emailSession, password);
        return AuthResponse.builder()
                .response(jwtService.getToken(user))
                .build();
    }
}
