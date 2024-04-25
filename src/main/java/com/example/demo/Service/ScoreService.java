package com.example.demo.Service;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.Score;

import java.util.List;
import java.util.Optional;

public interface ScoreService {
    List<Score> getAllScores();

    Score getScoreById(Integer id);

    Score createScore(Score score);

    Score updateScore(Integer id, Score score);

    void deleteScore(Integer id);

    Score createScoreForQuestion(Question question, Score score);
}
