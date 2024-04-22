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
        Score score = quizHistory.getScore();

        // Save the user, quiz, and score if they don't exist
        if (user != null && user.getId() != null) {
            user = userRepository.findById(user.getId()).orElse(user);
        } else {
            user = userRepository.save(user);
        }

        if (quiz != null && quiz.getIdquiz() != null) {
            quiz = quizRepository.findById(quiz.getIdquiz()).orElse(quiz);
        } else {
            quiz = quizRepository.save(quiz);
        }

        if (score != null && score.getIdscore() != null) {
            score = scoreRepository.findById(score.getIdscore()).orElse(score);
        } else {
            score = scoreRepository.save(score);
        }

        quizHistory.setUser(user);
        quizHistory.setQuiz(quiz);
        quizHistory.setScore(score);

        return quizHistoryRepository.save(quizHistory);
    }

    @Override
    public QuizHistory updateQuizHistory(Integer id, QuizHistory newQuizHistory) {
        Optional<QuizHistory> existingQuizHistory = quizHistoryRepository.findById(id);
        if (existingQuizHistory.isPresent()) {
            QuizHistory quizHistory = existingQuizHistory.get();
            quizHistory.setQuiz(newQuizHistory.getQuiz());
            quizHistory.setUser(newQuizHistory.getUser());
            quizHistory.setScore(newQuizHistory.getScore());
            return quizHistoryRepository.save(quizHistory);
        }
        return null;
    }

    @Override
    public void deleteQuizHistory(Integer id) {
        quizHistoryRepository.deleteById(id);
    }

    @Override
    public Integer getQuizScore(Integer iduser, Integer idquiz) {
        return quizHistoryRepository.findScoreByIdUserAndIdQuiz(iduser, idquiz);
    }

    @Override
    public int calculateScore(List<Question> questions, List<String> userAnswers) {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String userAnswer = userAnswers.get(i);
            if (question.getIndice_optionCorrecte() != null && userAnswer != null &&
                    question.getIndice_optionCorrecte() == Integer.parseInt(userAnswer)) {
                score++;
            }
        }
        return score;
    }
}
