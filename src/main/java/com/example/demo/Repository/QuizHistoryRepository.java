package com.example.demo.Repository;

import com.example.demo.Entity.QuizHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuizHistoryRepository extends JpaRepository<QuizHistory, Integer> {



    List<QuizHistory> findByUserId(Integer userId);
}


