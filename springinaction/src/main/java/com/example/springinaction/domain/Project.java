package com.example.springinaction.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
        columnNames = {"name"})})
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "proj", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    Set<Attribute> attributes;

    @OneToMany(mappedBy = "proj", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    Set<Geometry> geometries;

    public Set<Geometry> getGeometries() {
        return geometries;
    }

    public void setGeometries(Set<Geometry> geometries) {
        this.geometries = geometries;
    }

    public Project() {
    }


    public Project(String name) {
        this.name = name;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
