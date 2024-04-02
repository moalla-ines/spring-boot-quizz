package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_user")
public class TypeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeUser;

    private String value;

}
