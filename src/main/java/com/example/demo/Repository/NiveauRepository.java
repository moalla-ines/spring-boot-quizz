package com.example.demo.Repository;

import com.example.demo.Entity.Niveau;
import com.example.demo.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NiveauRepository extends JpaRepository<Niveau, Integer> {
    List<Niveau> findByCategorieIdcategorie(Integer idcategorie);
}
