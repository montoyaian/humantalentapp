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
@Table(name = "laboralInformation", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class LaboralInformation {
    @Id
    @Column(nullable = false)
    String ID;

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