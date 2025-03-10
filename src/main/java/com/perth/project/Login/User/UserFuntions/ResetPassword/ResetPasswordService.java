package com.perth.project.Login.User.UserFuntions.ResetPassword;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.User.UserFuntions.Notification.PasswordResetNotification;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Login.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    final JwtService jwtService;   
        @Autowired
    @Qualifier("emailSession")
    private final Session emailSession;
    public AuthResponse ResetPasswrodNotification(String Email){
        User user = userRepository.findByEmail(Email).orElse(null);
        if (user == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "Usuario no encontrado");
        }
        String token = jwtService.generateTemporaryToken(user);
        try {
            PasswordResetNotification.sendNotification(user.getEmail(), user.getUsername(),emailSession, token);
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "Error al enviar el correo");
        }
        
        return AuthResponse.builder()
                .response("Token de cambio de contraseña enviado")
                .build();
    }

    public AuthResponse ResetPassword(String token, String newPassword) {
        String username = jwtService.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> 
            new BusinessException(BusinessErrorCodes.BAD_REGISTER, "Usuario no encontrado"));
        
        if (!jwtService.isTokenValid(token, user)) {
            throw new BusinessException(BusinessErrorCodes.BAD_CREDENTIALS, "Token inválido o expirado");
        }
        
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        
        return AuthResponse.builder()
                .response("Contraseña cambiada exitosamente")
                .build();
    }
}
