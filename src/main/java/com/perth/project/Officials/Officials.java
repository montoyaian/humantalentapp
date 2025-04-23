package com.perth.project.Officials;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "officials", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Officials {
    @Id
    @Column(nullable = false)
    String UserId;

    @Basic
    @Column(nullable = false)
    String firstName;

    @Basic
    @Column(nullable = false)
    String lastName;

    @Basic
    @Column(nullable = false)
    String genre;

    @Basic
    @Column(nullable = false)
    String civilStatus;

    @Basic
    @Column(nullable = false)
    String phone;

    @Basic
    @Column(nullable = false)
    String email;

    @Basic
    @Column(nullable = false)
    Integer neighbourhoodID;

    @Basic
    @Column(nullable = false)
    String address;

    @Basic
    @Column(nullable = false)
    Date birthday;
    
    @Basic
    @Column(nullable = false)
    Boolean disability;

    @Basic
    @Column(nullable = false)
    String languages;

    @Basic
    @Column(nullable = false)
    String EPS;

    @Basic
    @Column(nullable = false)
    String AFP;

    @Basic
    @Column(nullable = false)
    String BloodType;

    @Basic
    @Column(nullable = false)
    String Syndicate;

    @Basic
    @Column(nullable = false)
    String Audited;

    @Basic
    @Column(nullable = false)
    String disease;

    @Basic
    @Column(nullable = false)
    String Hobby;

    @Basic
    @Column(nullable = false)
    String prePensioned;

    @Basic
    @Column(nullable = false)
    String HeadOfHousehold;

    @Basic
    @Column(nullable = false)
    Integer chargeID;

    @Basic
    @Column(nullable = false)
    Integer workAreaID;

    @Basic
    @Column(nullable = false)
    Integer grade;

    @Basic
    @Column(nullable = false)
    Float salary;

    @Basic
    @Column(nullable = false)
    String typeOfRelationship;

    @Basic
    @Column(nullable = false)
    Date dateOfEntry;

    @Basic
    @Column(nullable = false)
    boolean married;

    @Basic
    @Column(nullable = false)
    int sons;
    
    @Basic
    @Column(nullable = false)
    String profession;

    @Basic
    @Column(nullable = false)
    String lastStudyType;
}