package com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabourSupportResponse {
    private String ID;
    private String FirstName;
    private String LastName;
    private String TypeOfRelationship;
    private Date DateOfEntry;
    private int ChargeID;
    private Float Salary;
}