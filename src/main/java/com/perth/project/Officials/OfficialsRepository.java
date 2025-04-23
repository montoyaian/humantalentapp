package com.perth.project.Officials;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OfficialsRepository extends JpaRepository<Officials, String> {


}