package com.perth.project.Cv.AcademicSupport.AcademicSupportTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditAcademicSupport {
    @NotNull(message = "El tipo de formación es obligatorio")
    String TypeOfTraining;

    @NotNull(message = "El tipo de estudio es obligatorio")
    String TypeOfStudy;

    @NotNull(message = "La institución es obligatoria")
    String Institution;

    @NotNull(message = "La formación académica es obligatoria")
    String AcademicTraining;

    @NotNull(message = "El estado de graduado es obligatorio")
    boolean Graduate;


}