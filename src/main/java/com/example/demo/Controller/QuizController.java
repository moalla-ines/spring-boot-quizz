package com.example.demo.Controller;

import com.example.demo.Entity.Quizz;
import com.example.demo.Service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<Quizz>> getAllQuizzes() {
        List<Quizz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quizz> getQuizById(@PathVariable Integer id) {
        Optional<Quizz> quizOptional = quizService.getQuizById(id);
        return quizOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Quizz> createQuiz(@RequestBody Quizz quizz) {

        Quizz createdQuizz = quizService.createQuiz(quizz);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuizz);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quizz> updateQuiz(@PathVariable Integer id, @RequestBody Quizz updatedQuizz) {
        Quizz quizz = quizService.updateQuiz(id, updatedQuizz);
        return ResponseEntity.ok(quizz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Integer id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categorie/{idCategorie}")
    public ResponseEntity<List<Quizz>> getQuizzesByCategorie(@PathVariable Integer idCategorie) {
        List<Quizz> quizzes = quizService.getQuizzesByCategorie(idCategorie);
        return ResponseEntity.ok(quizzes);
    }

}
