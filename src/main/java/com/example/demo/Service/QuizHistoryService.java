package com.example.demo.Service;

import com.example.demo.Entity.QuizHistory;

import java.util.List;

public interface QuizHistoryService {
    List<QuizHistory> getAllQuizHistory();

    QuizHistory getQuizHistoryById(Integer id);

    QuizHistory createQuizHistory(QuizHistory quizHistory);

    QuizHistory updateQuizHistory(Integer id, QuizHistory quizHistory);

    void deleteQuizHistory(Integer id);
}
