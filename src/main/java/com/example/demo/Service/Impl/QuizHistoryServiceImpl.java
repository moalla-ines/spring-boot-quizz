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
        // Retrieve the user from the database if it exists
        UserEntity user = quizHistory.getUser();
        if (user != null && user.getId() != null) {
            Optional<UserEntity> existingUser = userRepository.findById(user.getId());
            if (existingUser.isPresent()) {
                user = existingUser.get();
            } else {
                user = userRepository.save(user); // Save the user if it doesn't exist
            }
        }

        // Retrieve the quiz from the database if it exists
        Quiz quiz = quizHistory.getQuiz();
        if (quiz != null && quiz.getId() != null) {
            Optional<Quiz> existingQuiz = quizRepository.findById(quiz.getId());
            if (existingQuiz.isPresent()) {
                quiz = existingQuiz.get();
            } else {
                quiz = quizRepository.save(quiz); // Save the quiz if it doesn't exist
            }
        }

        // Retrieve or save the score
        Score score = quizHistory.getScore();
        if (score != null && score.getIdscore() != null) {
            Optional<Score> existingScore = scoreRepository.findById(score.getIdscore());
            if (existingScore.isPresent()) {
                score = existingScore.get();
            } else {
                score = scoreRepository.save(score); // Save the score if it doesn't exist
            }
        }

        // Set the updated entities in the quiz history and save it
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
    public Integer getQuizScore(Integer userId, Integer quizId) {
        return quizHistoryRepository.findScoreByUserIdAndQuizId(userId, quizId);
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
