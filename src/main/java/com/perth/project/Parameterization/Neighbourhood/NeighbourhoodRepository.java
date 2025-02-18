package com.perth.project.Parameterization.Neighbourhood;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NeighbourhoodRepository extends JpaRepository<Neighbourhood, Integer> {
    Optional<Neighbourhood> findById(Integer id);
}