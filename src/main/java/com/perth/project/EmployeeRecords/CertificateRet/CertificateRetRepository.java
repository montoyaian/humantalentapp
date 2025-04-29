package com.perth.project.EmployeeRecords.CertificateRet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRetRepository extends JpaRepository<CertificateRet, String> {
    List<CertificateRet> findByuserId(String userId);
}
