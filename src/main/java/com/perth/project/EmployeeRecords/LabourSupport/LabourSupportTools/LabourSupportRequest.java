package com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabourSupportRequest {
    @NotNull(message = "El ID es obligatorio")
    String id;

    @NotNull(message = "El tipo de identidad es obligatorio")
    String typeIdentity;

    @NotNull(message = "El nombre de la empresa es obligatorio")
    String companyName;

    @NotNull(message = "El cargo es obligatorio")
    String charge;

    @NotNull(message = "La vinculación es obligatoria")
    String vinculation;

    @NotNull(message = "El tiempo de servicio es obligatorio")
    int timeService;

}