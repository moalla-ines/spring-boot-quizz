package com.example.demo.Repository;

import com.example.demo.Authentication.Token;
import com.example.demo.Entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    Optional<PasswordResetToken> findByToken(String token);
}
