package com.example.demo.Service.Impl;

import com.example.demo.Entity.Categorie;
import com.example.demo.Entity.Quiz;
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
        return quizRepository.save(quiz);
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
            updatedQuiz.setId_categorie(existingQuiz.getId_categorie());
            existingQuiz.setQuestions(updatedQuiz.getQuestions());
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
