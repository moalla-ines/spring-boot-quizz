package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iduser;

    private String username;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "idTypeUser")
    private TypeUser role;

    public User(Integer iduser, String username, String password, String email, TypeUser role) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String username, String password, String email, TypeUser role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User() {
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TypeUser getRole() {
        return role;
    }

    public void setRole(TypeUser role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}