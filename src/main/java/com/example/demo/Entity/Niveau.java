package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idniveau;
    private String name;

    public Niveau(Integer idniveau, String name) {
        this.idniveau = idniveau;
        this.name = name;
    }

    public Niveau(String name) {
        this.name = name;
    }

    public Niveau() {
    }


    public Integer getIdniveau() {
        return idniveau;
    }

    public void setIdniveau(Integer idniveau) {
        this.idniveau = idniveau;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Niveau{" +
                "idniveau=" + idniveau +
                ", name='" + name + '\'' +
                '}';
    }
}