package com.perth.project.Officials.AcademicInformation;

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
@Table(name = "academic_information", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class AcademicInformation {
    @Id
    @Column(nullable = false)
    String ID;

    @Basic
    @Column(nullable = false)
    String profession;

    @Basic
    @Column(nullable = false)
    String lastStudyType;
}