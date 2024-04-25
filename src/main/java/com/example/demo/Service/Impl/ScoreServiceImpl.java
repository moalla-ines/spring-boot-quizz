package com.example.demo.Service.Impl;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.Score;
import com.example.demo.Repository.ScoreRepository;
import com.example.demo.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    @Override
    public Score getScoreById(Integer id) {
        return scoreRepository.findById(id).orElse(null);
    }

    @Override
    public Score createScore(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public Score updateScore(Integer id, Score newScore) {
        Optional<Score> existingScore = scoreRepository.findById(id);
        if (existingScore.isPresent()) {
            Score score = existingScore.get();
            score.setValue(newScore.getValue());
            score.setQuestion(newScore.getQuestion());
            score.setQuizHistoryList(newScore.getQuizHistoryList());
            return scoreRepository.save(score);
        }
        return null;
    }

    @Override
    public void deleteScore(Integer id) {
        scoreRepository.deleteById(id);
    }

    @Override
    public Score createScoreForQuestion(Question question, Score score) {
        if (question != null && score != null) {
            score.setQuestion(question);
            return scoreRepository.save(score);
        } else {
            throw new IllegalArgumentException("Question and score must not be null");
        }
    }

}
