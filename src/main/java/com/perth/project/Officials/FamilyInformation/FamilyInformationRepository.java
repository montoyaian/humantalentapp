package com.perth.project.Officials.FamilyInformation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyInformationRepository extends JpaRepository<FamilyInformation, String> {
    Optional<FamilyInformation> findByID(String id);
}