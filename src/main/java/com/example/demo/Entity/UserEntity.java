package com.example.demo.Entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.LogManager;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;
@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
@JoinTable(name = "type_user",joinColumns =
@JoinColumn(name = "user_id",referencedColumnName = "id"),
inverseJoinColumns =@JoinColumn(name = "role_id", referencedColumnName = "idrole") )
private List<Role>roles = new ArrayList<>();


    public UserEntity(Integer id, String username, String password, String email,List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
this.roles=roles;
    }

    public UserEntity(String username, String password, String email,List<Role>roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles=roles;
    }

    public UserEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer iduser) {
        this.id = id;
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


    @Override
    public String toString() {
        return "User{" +
                "iduser=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +

                '}';
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}