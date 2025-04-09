package com.perth.project.EmployeeRecords.DetachablePayment;

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
@Table(name = "detachable_payment", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id" }) })
public class DetachablePayment {
    @Basic
    @Column(nullable = false)
    String user_id;

    @Basic
    @Column(nullable = false)
    String Month;

    @Basic
    @Column(nullable = false)
    int Year;

    @Id
    @Column(nullable = false)
    String detachable;
}
