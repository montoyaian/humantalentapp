package com.perth.project.Officials.PersonalInformation;

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
@Table(name = "personalInformation", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Personal_Information {
    @Id
    @Column(nullable = false)
    String ID;

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
    Integer phone;

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
}