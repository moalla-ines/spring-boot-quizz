package com.example.demo.Service;

import com.example.demo.Entity.Quiz;
import com.example.demo.Entity.Quizz;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    List<Quiz> getAllQuizzes();

    Optional<Quiz> getQuizById(Integer id);

    Quizz createQuiz(Quizz quizz);

    List<Quizz> getQuizzesByCategorie(Integer idCategorie);

    Quizz updateQuiz(Integer id, Quizz updatedQuizz);


    void deleteQuiz(Integer id);


}
