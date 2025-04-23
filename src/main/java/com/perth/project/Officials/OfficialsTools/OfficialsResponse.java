package com.perth.project.Officials.OfficialsTools;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficialsResponse {
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
    private Boolean disability;
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
    private Integer chargeID;
    private Integer workAreaID;
    private Integer grade;
    private Float salary;
    private String typeOfRelationship;
    private Date dateOfEntry;
    private boolean married;
    private int sons;
    private String profession;
    private String lastStudyType;
}