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
    public QuizHistory createQuizHistory(Integer iduser, Integer idquiz, Integer value) {
        System.out.println("user: "+iduser);
        System.out.println("idquiz: "+idquiz);



        // Assurez-vous que l'utilisateur existe en base de données
        Optional<UserEntity> existingUser = userRepository.findById(iduser);
        if (existingUser.isPresent()) {
            UserEntity user = existingUser.get();

            // Assurez-vous que le quiz existe en base de données
            Optional<Quiz> existingQuiz = quizRepository.findById(idquiz);
            if (existingQuiz.isPresent()) {
                Quiz quiz = existingQuiz.get();

                // Créer un nouvel historique de quiz
                QuizHistory quizHistory = new QuizHistory(value, user, quiz);

                // Enregistrer l'historique du quiz
                return quizHistoryRepository.save(quizHistory);
            } else {
                throw new IllegalArgumentException("Quiz not found with ID: " + idquiz);
            }
        } else {
            throw new IllegalArgumentException("User not found with ID: " + iduser);
        }
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

    @Override
    public List<QuizHistory> getQuizHistoryByIduser(Integer iduser) {
        return quizHistoryRepository.findByUserId(iduser);
    }



}
