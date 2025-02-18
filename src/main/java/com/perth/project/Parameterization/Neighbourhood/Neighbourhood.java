package com.perth.project.Parameterization.Neighbourhood;

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
@Table(name = "Neighbourhoods", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Neighbourhood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Basic
    @Column(nullable = false)
    String name;

    @Basic
    @Column(nullable = false)
    String location;

    @Basic
    @Column(nullable = false)
    String commune;
}