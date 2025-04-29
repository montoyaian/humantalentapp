package com.perth.project.Cv.Disabilities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DisabilitiesRepository extends JpaRepository<Disabilities, String> {
    Disabilities findBySupportDocument(String supportDocument);

    List<Disabilities> findByUserId(String userId);
}