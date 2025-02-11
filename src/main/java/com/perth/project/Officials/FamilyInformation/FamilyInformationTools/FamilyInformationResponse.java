package com.perth.project.Officials.FamilyInformation.FamilyInformationTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyInformationResponse {
    private String ID;
    private boolean married;
    private int sons;
}