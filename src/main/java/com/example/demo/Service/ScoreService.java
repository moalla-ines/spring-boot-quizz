package com.example.demo.Service;

import com.example.demo.Entity.Score;

import java.util.List;

public interface ScoreService {
    List<Score> getAllScores();

    Score getScoreById(Integer id);

    Score createScore(Score score);

    Score updateScore(Integer id, Score score);

    void deleteScore(Integer id);
}
