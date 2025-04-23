package com.perth.project.Cv.LaboralExperience.LaboralExperienceTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditLaboralExperience {
    @NotNull(message = "El tipo de identidad es obligatorio")
    private String typeIdentity;

    @NotNull(message = "El nombre de la empresa es obligatorio")
    private String companyName;

    @NotNull(message = "El cargo es obligatorio")
    private String charge;

    @NotNull(message = "La vinculación es obligatoria")
    private String vinculation;

    @NotNull(message = "El tiempo de servicio es obligatorio")
    private int timeService;

    @NotNull(message = "El documento de soporte es obligatorio")
    private String supportDocument;
}