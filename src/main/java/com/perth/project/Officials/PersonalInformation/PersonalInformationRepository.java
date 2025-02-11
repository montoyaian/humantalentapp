package com.perth.project.Officials.PersonalInformation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInformationRepository extends JpaRepository<Personal_Information, String> {
    Optional<Personal_Information> findByID(String id);
}