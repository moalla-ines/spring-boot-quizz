package com.example.demo.Repository;

import com.example.demo.Entity.QuizHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizHistoryRepository extends JpaRepository<QuizHistory, Integer> {
    Integer findScoreByUserIdAndQuizId(Integer userId, Integer quizId);
}
