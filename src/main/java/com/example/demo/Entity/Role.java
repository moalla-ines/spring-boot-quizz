package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity


@Table(name = "roles")
public class Role {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idrole;
private String name;

    public Role(Integer idrole, String name) {
        this.idrole = idrole;
        this.name = name;

    }

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
}
