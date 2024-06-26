package com.example.demo.Controller;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.Quiz;
import com.example.demo.Service.QuestionService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/questions")
@CrossOrigin()
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id) {
        Optional<Question> questionOptional = questionService.getQuestionById(id);
        return questionOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.createQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @PutMapping("/{idquestion}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer idquestion, @RequestBody Question updatedQuestion) {
        Question question = questionService.updateQuestion(idquestion, updatedQuestion);
        if (question != null) {
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idquestion}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer idquestion) {
        questionService.deleteQuestion(idquestion);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/quiz/{idquiz}")
    public ResponseEntity<List<Question>> findAllByIdQuiz(@PathVariable Integer idquiz) {
        List<Question> questions = questionService.findAllByIdQuiz(idquiz);
        return ResponseEntity.ok(questions);
    }

}