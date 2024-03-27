package com.example.demo.Dto;

import com.example.demo.Entity.TypeUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDto implements UserDetails {
    private Integer iduser;
    private String username;
    private String password;
    private String email;
    private TypeUser role;

    public UserDto(Integer iduser, String username, String password, String email, TypeUser role) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
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
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getValue())) ;
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
        return "UserDto{" +
                "iduser=" + iduser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role.getValue() + '\'' +
                '}';
    }
}