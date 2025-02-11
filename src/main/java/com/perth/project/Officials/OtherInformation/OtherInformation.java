package com.perth.project.Officials.OtherInformation;

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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "other_information", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class OtherInformation {
    @Id
    @Column(nullable = false)
    String ID;

    @Basic
    @Column(nullable = false)
    String disability;

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
}