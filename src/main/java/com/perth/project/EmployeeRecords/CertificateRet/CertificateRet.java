package com.perth.project.EmployeeRecords.CertificateRet;

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
@Table(name = "certificate_ret", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id" }) })
public class CertificateRet {
    @Id
    @Column(nullable = false)
    String user_id;

    @Basic
    @Column(nullable = false)
    int Year;

    @Basic
    @Column(nullable = false)
    String detachable;
}