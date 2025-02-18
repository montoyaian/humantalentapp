package com.perth.project.EmployeeRecords.AcademicSupport;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicSupportRepository extends JpaRepository<AcademicSupport, String> {
    Optional<AcademicSupport> findByID(String id);
}