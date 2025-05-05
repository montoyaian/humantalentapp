// filepath: c:\Users\monto\Documents\talentApp\src\main\java\com\perth\project\Cv\LaboralExperience\LaboralExperience.java
package com.perth.project.Cv.LaboralExperience;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "laboral_experience", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class LaboralExperience {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long ID;

    @Column(nullable = false)
    private String userId;

    @Basic
    @Column(nullable = false)
    private String TypeIdentity;

    @Basic
    @Column(nullable = false)
    private String CompanyName;

    @Basic
    @Column(nullable = false)
    private String Charge;

    @Basic
    @Column(nullable = false)
    private String Vinculation;

    @Basic
    @Column(nullable = false)
    private int TimeService;

    @Basic
    @Column(nullable = false)
    private String SupportDocument;
}