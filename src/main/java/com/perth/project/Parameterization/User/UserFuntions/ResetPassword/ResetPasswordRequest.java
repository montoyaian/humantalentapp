package com.perth.project.Parameterization.User.UserFuntions.ResetPassword;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {

    @NotNull(message = "El token es obligatorio")
    private String token;

    @NotNull(message = "La contraseña es obligatoria")
    private String password;
}
