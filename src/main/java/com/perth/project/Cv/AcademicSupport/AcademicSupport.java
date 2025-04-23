package com.perth.project.Cv.AcademicSupport;

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
@Table(name = "academic_support", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id" }) })
public class AcademicSupport {
    @Id
    @Column(nullable = false)
    String user_id;

    @Basic
    @Column(nullable = false)
    String TypeOfTraining;

    @Basic
    @Column(nullable = false)
    String TypeOfStudy;

    @Basic
    @Column(nullable = false)
    String Institution;

    @Basic
    @Column(nullable = false)
    String AcademicTraining;

    @Basic
    @Column(nullable = false)
    boolean Graduate;

    @Basic
    @Column(nullable = false)
    String SupportDocument;
}