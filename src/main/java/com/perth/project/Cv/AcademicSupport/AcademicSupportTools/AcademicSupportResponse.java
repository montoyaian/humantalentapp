package com.perth.project.Cv.AcademicSupport.AcademicSupportTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicSupportResponse {
    private Long ID;
    private String TypeOfTraining;
    private String TypeOfStudy;
    private String Institution;
    private String AcademicTraining;
    private boolean Graduate;
    private String SupportDocument;
    private String userId;
}