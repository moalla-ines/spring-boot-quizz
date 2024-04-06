package com.example.demo.Service.Impl;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.QuizHistory;
import com.example.demo.Entity.Score;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.QuizHistoryRepository;
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
    private ScoreRepository ScoreRepository;
    @Autowired
    private UserRepository userRepository;

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

        // Check if the user is null or if it has an ID (indicating it's already persisted)
        if (user != null && user.getId() == null) {
            // If the user is not yet persisted, save it first
            user = userRepository.save(user);

            // Update the quizHistory with the saved user
            quizHistory.setUser(user);
        }

        // Get the score from the quizHistory
        Score score = quizHistory.getScore();


        // Check if the score is null or if it has an ID (indicating it's already persisted)
        if (score != null && score.getIdscore() == null) {
            // If the score is not yet persisted, save it first
            score = ScoreRepository.save(score);

            // Update the quizHistory with the saved score
            quizHistory.setScore(score);

        }

        // Save the quizHistory (with the updated score reference)
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


}
