package com.perth.project.EmployeeRecords.DetachablePayment;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetachablePaymentRepository extends JpaRepository<DetachablePayment, String> {
    
    @Query("SELECT dp FROM DetachablePayment dp WHERE dp.user_id = :userId")
    List<DetachablePayment> findPaymentsByUserId(@Param("userId") String userId);

}