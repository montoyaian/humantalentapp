package com.perth.project.Parameterization.Area;

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
@Table(name = "area", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Area {
    @Id
    @Basic
    @Column(nullable = false)
    Integer code;
    @Column(nullable = false)
    String name;
}
