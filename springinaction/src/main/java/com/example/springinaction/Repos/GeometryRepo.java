package com.example.springinaction.Repos;

import com.example.springinaction.domain.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeometryRepo extends JpaRepository<Geometry, Long> {

}
