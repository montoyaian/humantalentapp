package com.perth.project.Parameterization.Area;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Integer>{
    Optional<Area> findByCode(Integer code);
}
