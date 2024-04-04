package com.example.demo.Service;

import com.example.demo.Entity.Quiz;


import java.util.List;
import java.util.Optional;

public interface QuizService {

    List<Quiz> getAllQuizzes();

    Optional<Quiz> getQuizById(Integer id);

    Quiz createQuiz(Quiz quizz);

    List<Quiz> getQuizzesByCategorie(Integer idCategorie);

    Quiz updateQuiz(Integer id, Quiz updatedQuizz);


    void deleteQuiz(Integer id);


}
