package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Niveau {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id_niveau;
private String name;

    public Niveau(Integer id_niveau, String name) {
        this.id_niveau = id_niveau;
        this.name = name;
    }

    public Niveau(String name) {
        this.name = name;
    }

    public Niveau() {
    }

    public Integer getId_niveau() {
        return id_niveau;
    }

    public void setId_niveau(Integer id_niveau) {
        this.id_niveau = id_niveau;
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
                "id_niveau=" + id_niveau +
                ", name='" + name + '\'' +
                '}';
    }
}
