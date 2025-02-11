package com.perth.project.Officials.OtherInformation.OtherInformationTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtherInformationRequest {
    @NotNull(message = "El ID es obligatorio")
    String id;

    @NotNull(message = "La discapacidad es obligatoria")
    String disability;

    @NotNull(message = "Los idiomas son obligatorios")
    String languages;

    @NotNull(message = "La EPS es obligatoria")
    String eps;

    @NotNull(message = "La AFP es obligatoria")
    String afp;

    @NotNull(message = "El tipo de sangre es obligatorio")
    String bloodType;

    @NotNull(message = "El sindicato es obligatorio")
    String syndicate;

    @NotNull(message = "La auditoría es obligatoria")
    String audited;

    @NotNull(message = "La enfermedad es obligatoria")
    String disease;

    @NotNull(message = "El hobby es obligatorio")
    String hobby;

    @NotNull(message = "El estado de pre-pensionado es obligatorio")
    String prePensioned;

    @NotNull(message = "El estado de jefe de hogar es obligatorio")
    String headOfHousehold;
}