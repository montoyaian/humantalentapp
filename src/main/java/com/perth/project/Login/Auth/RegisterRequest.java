package com.perth.project.Login.Auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull(message = "La identificación es obligatoria")
    private String id;
    
    @NotNull(message = "El nombre es obligatorio")
    private String firstName;

    @NotNull(message = "El apellido es obligatorio")
    private String lastName;

    @NotNull(message = "El perfil es obligatorio")
    private Integer profile;

    @NotNull(message = "El área es obligatoria")
    private Integer area;

    @NotNull(message = "El correo electrónico es obligatorio")
    private String email;
}
