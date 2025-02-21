package com.perth.project.Officials.LaboralInformation;

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
@Table(name = "laboral_information", uniqueConstraints = { @UniqueConstraint(columnNames = { "UserId" }) })
public class LaboralInformation {
    @Id
    @Column(name = "userId", nullable = false)
    String userId;

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
}