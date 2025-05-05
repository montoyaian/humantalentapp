package com.perth.project.Cv.LaboralExperience;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LaboralExperienceRepository extends JpaRepository<LaboralExperience, String> {

    List<LaboralExperience> findByuserId(String userId);
}