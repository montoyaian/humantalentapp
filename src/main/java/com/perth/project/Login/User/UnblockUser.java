package com.perth.project.Login.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnblockUser {
    private final UserRepository userRepository;

    public AuthResponse unblockUser(String userId) {
        if (userRepository.isAccountBlocked(userId)) {
            userRepository.unblockUser(userId);
            return AuthResponse.builder()
                    .response("Usuario desbloqueado correctamente.")
                    .build();
        }else{
            return AuthResponse.builder()
                    .response("El usuario no esta bloqueado.")
                    .build();
        }

    }

    public AuthResponse Unblock(String userId) {
        UserDetails user = userRepository.findByUsername(userId).orElse(null);

        if (user == null) {
            return AuthResponse.builder()
                    .response("Usuario no encontrado.")
                    .build();
        }else{
            return unblockUser(userId);
        }
    
    }
}
