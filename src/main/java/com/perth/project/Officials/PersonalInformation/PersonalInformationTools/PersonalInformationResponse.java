package com.perth.project.Officials.PersonalInformation.PersonalInformationTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInformationResponse {
    private String ID;
    private String firstName;
    private String lastName;
    private String genre;
    private String civilStatus;
    private String phone;
    private String email;
    private Integer neighbourhoodID;
    private String address;
    private Date birthday;
}