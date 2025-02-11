package com.perth.project.Officials.AcademicInformation.AcademicInformationTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicInformationRequest {
    @NotNull(message = "La profesión/ocupación es obligatoria")
    String id;

    @NotNull(message = "La profesión/ocupación es obligatoria")
    String profession;

    @NotNull(message = "El tipo de último estudio es obligatorio")
    String lastStudyType;
}
