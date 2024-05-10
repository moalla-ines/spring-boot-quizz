package com.example.demo.Dto;

import com.example.demo.Entity.Role;

import java.util.List;

public class UserDto {
    private Integer iduser;
    private String username;
    private String password;
    private String email;
    private List<Role> roles;

    public UserDto(Integer iduser, String username, String password, String email, List<Role> roles) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public UserDto() {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "iduser=" + iduser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
