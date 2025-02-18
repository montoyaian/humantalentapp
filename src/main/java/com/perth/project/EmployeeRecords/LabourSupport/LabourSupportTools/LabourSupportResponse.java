package com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabourSupportResponse {
    private String ID;
    private String TypeIdentity;
    private String CompanyName;
    private String Charge;
    private String Vinculation;
    private int TimeService;
    private String SupportDocument;
}