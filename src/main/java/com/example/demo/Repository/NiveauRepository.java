package com.example.demo.Repository;

import com.example.demo.Entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NiveauRepository extends JpaRepository<Niveau, Integer> {
}
