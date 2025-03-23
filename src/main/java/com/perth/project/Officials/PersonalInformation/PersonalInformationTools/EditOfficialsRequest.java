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
public class EditOfficialsRequest {
    
    @NotNull(message = "El nombre es obligatorio")
    String firstName;

    @NotNull(message = "El apellido es obligatorio")
    String lastName;

    @NotNull(message = "El género es obligatorio")
    String genre;

    @NotNull(message = "El estado civil es obligatorio")
    String civilStatus;

    @NotNull(message = "El teléfono es obligatorio")
    String phone;

    @NotNull(message = "El correo electrónico es obligatorio")
    String email;

    @NotNull(message = "El ID del vecindario es obligatorio")
    Integer neighbourhoodID;

    @NotNull(message = "La dirección es obligatoria")
    String address;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    Date birthday;

    @NotNull(message = "La discapacidad es obligatoria")
    Boolean disability;

    @NotNull(message = "Los idiomas son obligatorios")
    String languages;

    @NotNull(message = "El EPS es obligatorio")
    String EPS;

    @NotNull(message = "El AFP es obligatorio")
    String AFP;

    @NotNull(message = "El tipo de sangre es obligatorio")
    String BloodType;

    @NotNull(message = "El sindicato es obligatorio")
    String Syndicate;

    @NotNull(message = "La auditoría es obligatoria")
    String Audited;

    @NotNull(message = "La enfermedad es obligatoria")
    String disease;

    @NotNull(message = "El hobby es obligatorio")
    String Hobby;

    @NotNull(message = "El estado de pre-pensionado es obligatorio")
    String prePensioned;

    @NotNull(message = "El estado de jefe de hogar es obligatorio")
    String HeadOfHousehold;

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

    @NotNull(message = "El estado civil es obligatorio")
    boolean married;

    @NotNull(message = "El número de hijos es obligatorio")
    int sons;

    @NotNull(message = "La profesión es obligatoria")
    String profession;

    @NotNull(message = "El último tipo de estudio es obligatorio")
    String lastStudyType;
}