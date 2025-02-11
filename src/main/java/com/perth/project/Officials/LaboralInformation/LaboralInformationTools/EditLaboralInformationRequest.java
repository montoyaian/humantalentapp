package com.perth.project.Officials.LaboralInformation.LaboralInformationTools;

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
public class EditLaboralInformationRequest {

    @NotNull(message = "El ID del cargo es obligatorio")
    Integer chargeID;

    @NotNull(message = "El ID del área de trabajo es obligatorio")
    Integer workAreaID;

    @NotNull(message = "El grado es obligatorio")
    Integer grade;

    @NotNull(message = "El salario es obligatorio")
    Float salary;

    @NotNull(message = "El tipo de relación es obligatorio")
    String typeOfRelationship;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    Date dateOfEntry;
}