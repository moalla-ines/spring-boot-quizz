package com.example.demo.Controller;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.Quiz;
import com.example.demo.Entity.QuizHistory;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Service.QuizHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz-history")
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

    @PostMapping
    public ResponseEntity<QuizHistory> createQuizHistory(@RequestBody QuizHistoryRequest quizHistoryRequest) {
        // Convertir QuizHistoryRequest en UserEntity et Quiz
        UserEntity user = quizHistoryRequest.getUser();
        Quiz quiz = quizHistoryRequest.getQuiz();

        // Créer QuizHistory avec la méthode du service
        QuizHistory createdQuizHistory = quizHistoryService.createQuizHistory(user, quiz);

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

    @GetMapping("/user/{userId}/quiz/{quizId}/score")
    public ResponseEntity<Integer> getQuizScore(@PathVariable Integer userId, @PathVariable Integer quizId) {
        Integer score = quizHistoryService.getQuizScore(userId, quizId);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @PostMapping("/calculate-score")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<String> userAnswers) {
        List<Question> questions = null; // Récupérez les questions du quiz
        int score = quizHistoryService.calculateScore(questions, userAnswers);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    // Classe interne pour la demande de QuizHistory
    static class QuizHistoryRequest {
        private UserEntity user;
        private Quiz quiz;

        // Getters et setters
        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public Quiz getQuiz() {
            return quiz;
        }

        public void setQuiz(Quiz quiz) {
            this.quiz = quiz;
        }
    }
}
