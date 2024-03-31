package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {


    private String password;
    private String username;




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


    }


