package com.perth.project.Officials.PersonalInformation.PersonalInformationTools;

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
public class OfficialsRequest {
    
    @NotNull(message = "la cedula es obligatoria")
    String id;

    @NotNull(message = "el nombre es obligatorio")
    String firstName;

    @NotNull(message = "el apellido es obligatorio")
    String lastName;

    @NotNull(message = "el género es obligatorio")
    String genre;

    @NotNull(message = "el estado civil es obligatorio")
    String civilStatus;

    @NotNull(message = "el teléfono es obligatorio")
    String phone;

    @NotNull(message = "el correo electrónico es obligatorio")
    String email;

    @NotNull(message = "el id del vecindario es obligatorio")
    Integer neighbourhoodID;

    @NotNull(message = "la dirección es obligatoria")
    String address;

    @NotNull(message = "la fecha de nacimiento es obligatoria")
    Date birthday;

    @NotNull(message = "la discapacidad es obligatoria")
    Boolean disability;

    @NotNull(message = "los idiomas son obligatorios")
    String languages;

    @NotNull(message = "el eps es obligatorio")
    String eps;

    @NotNull(message = "el afp es obligatorio")
    String afp;

    @NotNull(message = "el tipo de sangre es obligatorio")
    String bloodType;

    @NotNull(message = "el sindicato es obligatorio")
    String syndicate;

    @NotNull(message = "la auditoría es obligatoria")
    String audited;

    @NotNull(message = "la enfermedad es obligatoria")
    String disease;

    @NotNull(message = "el hobby es obligatorio")
    String hobby;

    @NotNull(message = "el estado de pre-pensionado es obligatorio")
    String prePensioned;

    @NotNull(message = "el estado de jefe de hogar es obligatorio")
    String headOfHousehold;

    @NotNull(message = "el id del cargo es obligatorio")
    Integer chargeID;

    @NotNull(message = "el id del área de trabajo es obligatorio")
    Integer workAreaID;

    @NotNull(message = "el grado es obligatorio")
    Integer grade;

    @NotNull(message = "el salario es obligatorio")
    Float salary;

    @NotNull(message = "el tipo de relación es obligatorio")
    String typeOfRelationship;

    @NotNull(message = "la fecha de ingreso es obligatoria")
    Date dateOfEntry;

    @NotNull(message = "el estado civil es obligatorio")
    boolean married;

    @NotNull(message = "el número de hijos es obligatorio")
    int sons;

    @NotNull(message = "la profesión es obligatoria")
    String profession;

    @NotNull(message = "el último tipo de estudio es obligatorio")
    String lastStudyType;
}