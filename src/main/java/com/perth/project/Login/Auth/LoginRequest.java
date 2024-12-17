package com.perth.project.Login.Auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequest {
    @NotNull(message = "El nombre de usuario es obligatorio")
    String username;
    @NotNull(message = "La contraseña es obligatoria")
    String password;
}
