package com.example.demo.Controller;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.QuizHistory;
import com.example.demo.Service.QuizHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/quiz-history")
@CrossOrigin( )
public class QuizHistoryController {

    @Autowired
    private QuizHistoryService quizHistoryService;

    @GetMapping
    public ResponseEntity<List<QuizHistory>> getAllQuizHistory() {
        List<QuizHistory> quizHistoryList = quizHistoryService.getAllQuizHistory();
        return new ResponseEntity<>(quizHistoryList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizHistory> getQuizHistoryById(@PathVariable Integer id) {
        QuizHistory quizHistory = quizHistoryService.getQuizHistoryById(id);
        return new ResponseEntity<>(quizHistory, HttpStatus.OK);
    }
    @GetMapping("/user/{iduser}")
    public ResponseEntity<List<QuizHistory>> getQuizHistoryByIduser(@PathVariable Integer iduser) {
        List<QuizHistory> quizHistoryList = quizHistoryService.getQuizHistoryByIduser(iduser);
        return new ResponseEntity<>(quizHistoryList, HttpStatus.OK);
    }



    @PostMapping("/{iduser}/{idquiz}")
    public ResponseEntity<QuizHistory> createQuizHistory(@PathVariable Integer iduser , @PathVariable Integer idquiz,@RequestBody Integer value) {
        QuizHistory createdQuizHistory = quizHistoryService.createQuizHistory(iduser,idquiz,value);
        return new ResponseEntity<>(createdQuizHistory, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<QuizHistory> updateQuizHistory(@PathVariable Integer id, @RequestBody QuizHistory quizHistory) {
        QuizHistory updatedQuizHistory = quizHistoryService.updateQuizHistory(id, quizHistory);
        return new ResponseEntity<>(updatedQuizHistory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizHistory(@PathVariable Integer id) {
        quizHistoryService.deleteQuizHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
