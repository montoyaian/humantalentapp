package com.perth.project.Cv.Disabilities;

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

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "disabilities", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId" }) })
public class Disabilities {
    @Id
    @Column(nullable = false)
    String userId;

    @Basic
    @Column(nullable = false)
    String typeOfDisease;

    @Basic
    @Column(nullable = false)
    LocalDate startDate;

    @Basic
    @Column(nullable = false)
    LocalDate endDate;

    @Basic
    @Column(nullable = false)
    int daysOfIncapacity;

    @Basic
    @Column(nullable = false)
    String eps;

    @Basic
    @Column(nullable = false)
    String supportDocument;
}