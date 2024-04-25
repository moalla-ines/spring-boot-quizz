package com.example.demo.Controller;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.Score;
import com.example.demo.Service.QuestionService;
import com.example.demo.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/score")
@CrossOrigin()
public class ScoreController {

    @Autowired
    private ScoreService scoreService;
    private QuestionService questionService;

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

    @PostMapping("/questions/{idquestion}")
    public ResponseEntity<Score> createScoreForQuestion(@PathVariable Integer idquestion, @RequestBody Score score) {
        // Ajoutez le code pour récupérer la question correspondant à l'idquestion
        Optional<Question> optionalQuestion = questionService.getQuestionById(idquestion);

        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            // Enregistrez le score pour cette question
            // Vous devez ajuster votre service pour qu'il prenne en compte l'ID de la question
            Score createdScore = scoreService.createScoreForQuestion(question, score);

            // Retourner le score créé
            return new ResponseEntity<>(createdScore, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
