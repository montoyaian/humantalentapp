package com.perth.project.Officials.PersonalInformation.PersonalInformationTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInformationRequest {
    

    @NotNull(message = "La cedula es obligatoria")
    String id;

    @NotNull(message = "El nombre es obligatorio")
    String firstName;

    @NotNull(message = "El apellido es obligatorio")
    String lastName;

    @NotNull(message = "El género es obligatorio")
    String genre;

    @NotNull(message = "El estado civil es obligatorio")
    String civilStatus;

    @NotNull(message = "El teléfono es obligatorio")
    Integer phone;

    @NotNull(message = "El correo electrónico es obligatorio")
    String email;

    @NotNull(message = "El ID del vecindario es obligatorio")
    Integer neighbourhoodID;

    @NotNull(message = "La dirección es obligatoria")
    String address;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    Date birthday;
}