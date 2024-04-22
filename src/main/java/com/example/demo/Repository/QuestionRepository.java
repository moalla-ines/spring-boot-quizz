package com.example.demo.Repository;

import com.example.demo.Entity.Niveau;
import com.example.demo.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query(value = "SELECT * FROM question WHERE idquiz = :idquiz", nativeQuery = true)
    List<Question> findAllByIdQuiz(@Param("idquiz") Integer id  );

}
