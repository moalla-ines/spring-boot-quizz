package com.example.demo.Service;

import com.example.demo.Config.QuizNotFoundException;
import com.example.demo.Dto.QuizDto;
import com.example.demo.Entity.Quiz;


import java.util.List;
import java.util.Optional;

public interface QuizService {

    List<Quiz> getAllQuizzes();

    Optional<Quiz> getQuizById(Integer id);



    List<Quiz> getQuizzesByCategorie(Integer idcategorie);

    Quiz createQuiz(Quiz quiz) throws QuizNotFoundException;

    Quiz updateQuiz(Integer id, Quiz updatedQuizz);


    void deleteQuiz(Integer id);


    List<Quiz> getQuizzesByNiveau(Integer idNiveau);
}
