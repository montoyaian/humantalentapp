package com.perth.project.Officials.FamilyInformation.FamilyInformationTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FamilyInformationRequest {
    @NotNull(message = "El ID es obligatorio")
    String id;

    @NotNull(message = "El estado civil es obligatorio")
    boolean married;

    @NotNull(message = "El número de hijos es obligatorio")
    int sons;
}