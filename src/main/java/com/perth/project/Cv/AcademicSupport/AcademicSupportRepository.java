package com.perth.project.Cv.AcademicSupport;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AcademicSupportRepository extends JpaRepository<AcademicSupport, String> {
    List<AcademicSupport> findByuserId(String userId);
}