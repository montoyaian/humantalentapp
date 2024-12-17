package com.perth.project.Login.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnblockUser {
    private final UserRepository userRepository;

    public AuthResponse Unblock(String userId) {
        UserDetails user = userRepository.findByUsername(userId).orElse(null);

        if (user == null) {
            return AuthResponse.builder()
                    .response("Usuario no encontrado.")
                    .build();
        }
        userRepository.UnblockAccount(userId);
        return AuthResponse.builder()
                .response("Usuario desbloqueado correctamente.")
                .build();
    }
}
