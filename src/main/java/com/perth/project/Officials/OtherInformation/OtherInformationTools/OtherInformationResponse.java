package com.perth.project.Officials.OtherInformation.OtherInformationTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherInformationResponse {
    private String ID;
    private String disability;
    private String languages;
    private String EPS;
    private String AFP;
    private String BloodType;
    private String Syndicate;
    private String Audited;
    private String disease;
    private String Hobby;
    private String prePensioned;
    private String HeadOfHousehold;
}