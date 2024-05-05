package com.example.demo.Service.Impl;

import com.example.demo.Entity.*;
import com.example.demo.Repository.QuizHistoryRepository;
import com.example.demo.Repository.QuizRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.ScoreRepository;
import com.example.demo.Service.QuizHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizHistoryServiceImpl implements QuizHistoryService {

    @Autowired
    private QuizHistoryRepository quizHistoryRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public List<QuizHistory> getAllQuizHistory() {
        return quizHistoryRepository.findAll();
    }

    @Override
    public QuizHistory getQuizHistoryById(Integer id) {
        return quizHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public QuizHistory createQuizHistory(QuizHistory quizHistory) {

        UserEntity user = quizHistory.getUser();
        Quiz quiz = quizHistory.getQuiz();

        // Assurez-vous que l'utilisateur existe en base de données
        if (user != null && user.getId() != null) {
            Optional<UserEntity> existingUser = userRepository.findById(user.getId());
            if (existingUser.isPresent()) {
                user = existingUser.get();
            } else {
                throw new IllegalArgumentException("User not found with ID: " + user.getId());
            }
        } else {
            throw new IllegalArgumentException("User ID is required");
        }

        // Assurez-vous que le quiz existe en base de données
        if (quiz != null && quiz.getIdquiz() != null) {
            Optional<Quiz> existingQuiz = quizRepository.findById(quiz.getIdquiz());
            if (existingQuiz.isPresent()) {
                quiz = existingQuiz.get();
            } else {
                throw new IllegalArgumentException("Quiz not found with ID: " + quiz.getIdquiz());
            }
        } else {
            throw new IllegalArgumentException("Quiz ID is required");
        }

        // Met à jour les références dans l'historique du quiz
        quizHistory.setUser(user);
        quizHistory.setQuiz(quiz);

        return quizHistoryRepository.save(quizHistory);
    }


    @Override
    public QuizHistory updateQuizHistory(Integer id, QuizHistory newQuizHistory) {
        Optional<QuizHistory> existingQuizHistory = quizHistoryRepository.findById(id);
        if (existingQuizHistory.isPresent()) {
            QuizHistory quizHistory = existingQuizHistory.get();
            quizHistory.setQuiz(newQuizHistory.getQuiz());
            quizHistory.setUser(newQuizHistory.getUser());

            return quizHistoryRepository.save(quizHistory);
        }
        return null;
    }

    @Override
    public void deleteQuizHistory(Integer id) {
        quizHistoryRepository.deleteById(id);
    }


}
