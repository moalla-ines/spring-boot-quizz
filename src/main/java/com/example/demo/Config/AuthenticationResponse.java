package com.example.demo.Config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class AuthenticationResponse {
    private String token;

    public void setToken(String jwtToken) {
    }

    public String getToken() {
        return token;
    }
}
