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
public class EditLabourSupport {
    @NotNull(message = "El tipo de identidad es obligatorio")
    String TypeIdentity;

    @NotNull(message = "El nombre de la empresa es obligatorio")
    String CompanyName;

    @NotNull(message = "El cargo es obligatorio")
    String Charge;

    @NotNull(message = "La vinculación es obligatoria")
    String Vinculation;

    @NotNull(message = "El tiempo de servicio es obligatorio")
    int TimeService;
}
