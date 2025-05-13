package com.perth.project.Cv.Disabilities.DisabilitiesTools;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisabilitiesResponse {
    private Long id;
    private String typeOfDisease;
    private LocalDate startDate;
    private LocalDate endDate;
    private int daysOfIncapacity;
    private String eps;
    private String supportDocument;
    private String userId;
}