package com.example.demo.Controller;

import com.example.demo.Config.QuizNotFoundException;
import com.example.demo.Dto.QuizDto;
import com.example.demo.Entity.Question;
import com.example.demo.Entity.Quiz;
import com.example.demo.Service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/quiz")
@CrossOrigin( )
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Integer id) {
        Optional<Quiz> quizOptional = quizService.getQuizById(id);
        return quizOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz ) throws QuizNotFoundException {

        Quiz createdQuiz = quizService.createQuiz(quiz);
        System.out.println(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);

    }


    @PutMapping("/{idquiz}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Integer idquiz, @RequestBody Quiz updatedQuiz) {
        Quiz quiz = quizService.updateQuiz(idquiz, updatedQuiz);
        return ResponseEntity.ok(quiz);
    }

    @DeleteMapping("/{idquiz}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable("idquiz") Integer idquiz) {
        quizService.deleteQuiz(idquiz);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categorie/{idcategorie}")
    public ResponseEntity<List<Quiz>> getQuizzesByCategorie(@PathVariable Integer idcategorie) {
        List<Quiz> quizzes = quizService.getQuizzesByCategorie(idcategorie);
        return ResponseEntity.ok(quizzes);
    }
    @GetMapping("/niveau/{idNiveau}")
    public ResponseEntity<List<Quiz>> getQuizzesByNiveau(@PathVariable Integer idNiveau) {
        List<Quiz> quizzes = quizService.getQuizzesByNiveau(idNiveau);
        return ResponseEntity.ok(quizzes);
    }

}
