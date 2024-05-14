package com.example.demo.Authentication;

import com.example.demo.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    private Integer id ;
    private String token ;
    private LocalDateTime createAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validateAt;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user ;
}
