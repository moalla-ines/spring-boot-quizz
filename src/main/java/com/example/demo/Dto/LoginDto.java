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
    private String email;

    public LoginDto(String password, String email) {
        this.password = password;
        this.email = email;

    }

    public LoginDto() {
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
        return "LoginDto{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

