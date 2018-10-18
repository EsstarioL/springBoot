package com.example.springinaction.Repos;

import com.example.springinaction.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepo extends JpaRepository<Attribute, Long> {

}
