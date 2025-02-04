package com.perth.project.Parameterization.Charge;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepository extends JpaRepository<Charge, Integer> {
    Optional<Charge> findById(Integer id);
    
}
