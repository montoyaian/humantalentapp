package com.perth.project.EmployeeRecords.LabourSupport;

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
@Table(name = "labour_support", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id" }) })
public class LabourSupport {
    @Id
    @Column(nullable = false)
    String user_id;

    @Basic
    @Column(nullable = false)
    String TypeIdentity;

    @Basic
    @Column(nullable = false)
    String CompanyName;

    @Basic
    @Column(nullable = false)
    String Charge;

    @Basic
    @Column(nullable = false)
    String Vinculation;

    @Basic
    @Column(nullable = false)
    int TimeService;

    @Basic
    @Column(nullable = false)
    String SupportDocument;
}
