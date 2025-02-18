package com.perth.project.EmployeeRecords.AcademicSupport.AcademicSupportTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicSupportRequest {
    @NotNull(message = "El ID es obligatorio")
    String id;

    @NotNull(message = "El tipo de formación es obligatorio")
    String typeOfTraining;

    @NotNull(message = "El tipo de estudio es obligatorio")
    String typeOfStudy;

    @NotNull(message = "La institución es obligatoria")
    String institution;

    @NotNull(message = "La formación académica es obligatoria")
    String academicTraining;

    @NotNull(message = "El estado de graduado es obligatorio")
    boolean graduate;

}
