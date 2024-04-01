package com.example.demo.Service.Impl;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.Quiz;
import com.example.demo.Repository.QuestionRepository;
import com.example.demo.Repository.QuizRepository;
import com.example.demo.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;



    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> getQuizById(Integer id) {
        return quizRepository.findById(id);
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        // Récupérer le quiz existant en fonction de son ID
        Optional<Quiz> existingQuizOptional = quizRepository.findById(quiz.getIdquiz());

        if (existingQuizOptional.isPresent()) {
            // Mettre à jour l'objet existingQuiz avec les nouvelles données de quiz
            Quiz existingQuiz = existingQuizOptional.get();
            existingQuiz.setCategorie(quiz.getCategorie());
            existingQuiz.setQuestions(quiz.getQuestions());
            for (Question question : existingQuiz.getQuestions()) {
                question.setQuiz(existingQuiz);
            }

            // Sauvegarder le quiz mis à jour
            return quizRepository.save(existingQuiz);
        } else {
            // Le quiz n'existe pas, donc sauvegarder le nouveau quiz
            return quizRepository.save(quiz);
        }
    }



    @Override
    public List<Quiz> getQuizzesByCategorie(Integer idCategorie) {
        return null;
    }


    @Override
    public Quiz updateQuiz(Integer id, Quiz updatedQuiz) {
        Optional<Quiz> existingQuizOptional = quizRepository.findById(id);
        if (existingQuizOptional.isPresent()) {
            Quiz existingQuiz = existingQuizOptional.get();
            updatedQuiz.setCategorie(existingQuiz.getCategorie());
            updatedQuiz.setQuestions(existingQuiz.getQuestions());
            return quizRepository.save(existingQuiz);
        } else {
            throw new IllegalArgumentException("Quiz not found with id " + id);
        }
    }




    @Override
    public void deleteQuiz(Integer id) {
        quizRepository.deleteById(id);
    }


}
