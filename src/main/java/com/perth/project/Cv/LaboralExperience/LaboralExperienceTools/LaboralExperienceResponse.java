package com.perth.project.Cv.LaboralExperience.LaboralExperienceTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboralExperienceResponse {
    private long ID;
    private String TypeIdentity;
    private String CompanyName;
    private String Charge;
    private String Vinculation;
    private int TimeService;
    private String SupportDocument;
    private String userId;
}