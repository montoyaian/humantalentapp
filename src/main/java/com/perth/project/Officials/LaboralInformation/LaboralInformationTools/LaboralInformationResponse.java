package com.perth.project.Officials.LaboralInformation.LaboralInformationTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboralInformationResponse {
    private String ID;
    private Integer chargeID;
    private Integer workAreaID;
    private Integer grade;
    private Float salary;
    private String typeOfRelationship;
    private Date dateOfEntry;
}