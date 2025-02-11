package com.perth.project.Officials.AcademicInformation.AcademicInformationTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicInformationResponse {
    private String ID;
    private String profession;
    private String lastStudyType;
}