package com.perth.project.Cv.Disabilities.DisabilitiesTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditDisabilities {
    @NotNull(message = "El tipo de enfermedad es obligatorio")
    String typeOfDisease;

    @NotNull(message = "La fecha de inicio es obligatoria")
    LocalDate startDate;

    @NotNull(message = "La fecha de fin es obligatoria")
    LocalDate endDate;

    @NotNull(message = "La EPS es obligatoria")
    String eps;
}