package com.example.springinaction.Repos;

import com.example.springinaction.domain.Geometry;
import com.example.springinaction.domain.Project;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GeometryRepo extends JpaRepository<Geometry, Long> {

}
