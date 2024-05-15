package com.example.demo.Authentication;

import com.example.demo.Config.AuthenticationResponse;
import com.example.demo.Config.JwtService;
import com.example.demo.Config.UnauthorizedException;
import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.TokenRepository;
import com.example.demo.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

@Service

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
private  final TokenRepository tokenRepository;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }


    public AuthenticationResponse register(UserDto request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Role role = new Role("user");
        UserEntity.UserEntityBuilder builder = UserEntity.builder();
        builder.username(request.getUsername());
        builder.password(passwordEncoder.encode(request.getPassword()));
        builder.email(request.getEmail());
        builder.roles(Collections.singletonList(role));
        builder.existe(1);
        var user = builder.build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .role(role.getName())
                .build();
    }

    private void sendValidationEmail(UserEntity user) {
        var newToken =generateAndSaveActivationToken(user);
        //send email
    }

    private String generateAndSaveActivationToken(UserEntity user) {
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characteres = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i< length ; i++){
            int randomIndex = secureRandom.nextInt(characteres.length());
            codeBuilder.append(characteres.charAt(randomIndex));
        }
        return  codeBuilder.toString();
    }


    public AuthenticationResponse authenticate(LoginDto request) {
        System.out.println(request);
        System.out.println("mdp saisie "+passwordEncoder.encode(request.getPassword()));
        UserEntity user1 = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));
        System.out.println("mdp stocké " +user1.getPassword());
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
                .id(user.getId())
                .role(user.getRoles().get(0).getName()) // Supposant que l'utilisateur a un seul rôle
                .build();

    }

}