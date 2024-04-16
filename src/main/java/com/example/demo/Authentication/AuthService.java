package com.example.demo.Authentication;

import com.example.demo.Config.AuthenticationResponse;
import com.example.demo.Config.JwtService;
import com.example.demo.Config.UnauthorizedException;
import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponse register(UserDto request) {
        Role role = new Role("user");
        UserEntity.UserEntityBuilder builder = UserEntity.builder();
        builder.username(request.getUsername());
        builder.password(passwordEncoder.encode(request.getPassword()));
        builder.email(request.getEmail());
        builder.roles(Collections.singletonList(role));
        var user = builder
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(LoginDto request) {
        System.out.println(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        var jwtToken = jwtService.generateToken(user);
        System.out.println(jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}