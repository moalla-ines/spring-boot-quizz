package com.example.demo.Service.Impl;

import com.example.demo.Entity.*;
import com.example.demo.Repository.QuizHistoryRepository;
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
    private UserEntity user;
    private Quiz quiz;

    public QuizHistoryServiceImpl(QuizHistoryRepository quizHistoryRepository, ScoreRepository scoreRepository) {
        this.quizHistoryRepository = quizHistoryRepository;
        this.scoreRepository = scoreRepository;
    }



    @Override
    public List<QuizHistory> getAllQuizHistory() {
        return quizHistoryRepository.findAll();
    }

    @Override
    public QuizHistory getQuizHistoryById(Integer id) {
        return quizHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public QuizHistory createQuizHistory(UserEntity user, Quiz quiz) {
        this.user = user;
        this.quiz = quiz;
        Score score = new Score();
        score.setValue(10);
        scoreRepository.save(score);

        QuizHistory quizHistory = new QuizHistory(user, quiz, score);
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

    @Override
    public void createQuizHistory(QuizHistory quizHistory) {
        quizHistoryRepository.save(quizHistory);
    }
}
