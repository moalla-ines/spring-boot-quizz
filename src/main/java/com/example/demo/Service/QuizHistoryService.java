package com.example.demo.Service;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.QuizHistory;

import java.util.List;

public interface QuizHistoryService {
    List<QuizHistory> getAllQuizHistory();

    QuizHistory getQuizHistoryById(Integer id);

    QuizHistory createQuizHistory(QuizHistory quizHistory);

    QuizHistory updateQuizHistory(Integer id, QuizHistory quizHistory);

    void deleteQuizHistory(Integer id);

    Integer getQuizScore(Integer userId, Integer quizId);

    int calculateScore(List<Question> questions, List<String> userAnswers);
}
