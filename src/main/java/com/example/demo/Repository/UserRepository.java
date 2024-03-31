package com.example.demo.Repository;

import com.example.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}
