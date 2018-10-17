package com.example.springinaction.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
@Entity
public class Attribute {
    private String name;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @JsonManagedReference
    private Project proj;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "geometry_id")
    @JsonManagedReference
    private Geometry geom;



    public Attribute() {
    }

    public Attribute(String name) {
        this.name = name;
    }

    public Project getProj() {
        return proj;
    }

    public void setProj(Project proj) {
        this.proj = proj;
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

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }
}
