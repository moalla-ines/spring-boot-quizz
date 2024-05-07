package com.example.demo.Service;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.QuizHistory;

import java.util.List;

public interface QuizHistoryService {
    List<QuizHistory> getAllQuizHistory();

    QuizHistory getQuizHistoryById(Integer id);


    QuizHistory createQuizHistory(Integer iduser,Integer idquiz, Integer value);

    QuizHistory updateQuizHistory(Integer id, QuizHistory quizHistory);

    void deleteQuizHistory(Integer id);


    List<QuizHistory>  getQuizHistoryByIduser(Integer iduser);
}
