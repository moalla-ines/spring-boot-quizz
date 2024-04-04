package com.example.demo.Service;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.Quizz;
import com.example.demo.Entity.QuizHistory;
import com.example.demo.Entity.UserEntity;

import java.util.List;

public interface QuizHistoryService {
    QuizHistory createQuizHistory(UserEntity user, Quizz quizz);

    List<QuizHistory> getAllQuizHistory();

    QuizHistory getQuizHistoryById(Integer id);

    QuizHistory updateQuizHistory(Integer id, QuizHistory newQuizHistory);

    void deleteQuizHistory(Integer id);

    Integer getQuizScore(Integer userId, Integer quizId);

    int calculateScore(List<Question> questions, List<String> userAnswers);

    void createQuizHistory(QuizHistory quizHistory);
}
