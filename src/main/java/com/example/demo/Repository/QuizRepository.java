package com.example.demo.Repository;

import com.example.demo.Entity.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByNiveauIdNiveau(Integer idNiveau);

    List<Quiz> findByCategorieIdcategorie(Integer idcategorie);
}
