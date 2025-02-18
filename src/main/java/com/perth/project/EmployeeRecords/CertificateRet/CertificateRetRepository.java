package com.perth.project.EmployeeRecords.CertificateRet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRetRepository extends JpaRepository<CertificateRet, String> {
    Optional<CertificateRet> findByID(String id);
}
