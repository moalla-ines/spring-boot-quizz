package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TypeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeUser;
    private String Value;

    public TypeUser() {

    }

    public Integer getIdTypeUser() {
        return idTypeUser;
    }

    public void setIdTypeUser(Integer idTypeUser) {
        this.idTypeUser = idTypeUser;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public TypeUser(Integer idTypeUser, String value) {
        this.idTypeUser = idTypeUser;
        Value = value;
    }
}
