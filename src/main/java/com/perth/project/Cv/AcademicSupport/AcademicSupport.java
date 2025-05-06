package com.perth.project.Cv.AcademicSupport;

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
@Table(name = "academic_support", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class AcademicSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Basic
    @Column(nullable = false)
    private String TypeOfTraining;

    @Basic
    @Column(nullable = false)
    private String TypeOfStudy;

    @Basic
    @Column(nullable = false)
    private String Institution;

    @Basic
    @Column(nullable = false)
    private String AcademicTraining;

    @Basic
    @Column(nullable = false)
    private boolean Graduate;

    @Basic
    @Column(nullable = false)
    private String SupportDocument;

    @Basic
    @Column(nullable = false)
    private String userId;
} 