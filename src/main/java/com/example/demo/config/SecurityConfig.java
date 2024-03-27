package com.example.demo.config;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthentificationFilter jwtAuthFilter;
    private final AuthenticationProvider authentificationProvider;

    public SecurityConfig(JwtAuthentificationFilter jwtAuthFilter, AuthenticationProvider authentificationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authentificationProvider = authentificationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.csrf()
               .disable()
               .authorizeHttpRequests()
               .requestMatchers("")
               .permitAll()
               .anyRequest()
               .authenticated()
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authenticationProvider(authentificationProvider)
               .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);





        return httpSecurity.build();
    }
}
