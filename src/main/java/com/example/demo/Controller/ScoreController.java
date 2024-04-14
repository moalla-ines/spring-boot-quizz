package com.example.demo.Controller;

import com.example.demo.Entity.Score;
import com.example.demo.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
@CrossOrigin( origins = "http://localhost:57965")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping
    public ResponseEntity<List<Score>> getAllScores() {
        List<Score> scores = scoreService.getAllScores();
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable Integer id) {
        Score score = scoreService.getScoreById(id);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Score> createScore(@RequestBody Score score) {
        Score createdScore = scoreService.createScore(score);
        return new ResponseEntity<>(createdScore, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Score> updateScore(@PathVariable Integer id, @RequestBody Score score) {
        Score updatedScore = scoreService.updateScore(id, score);
        return new ResponseEntity<>(updatedScore, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Integer id) {
        scoreService.deleteScore(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
