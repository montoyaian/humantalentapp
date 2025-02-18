package com.perth.project.EmployeeRecords.DetachablePayment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetachablePaymentRepository extends JpaRepository<DetachablePayment, String> {
    Optional<DetachablePayment> findByID(String id);
}