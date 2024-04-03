package com.example.demo.Controller;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.QuizHistory;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Service.QuizHistoryService;
import com.example.demo.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final QuizHistoryService quizHistoryService;

    @Autowired
    public UserController(UserService userService, QuizHistoryService quizHistoryService) {
        this.userService = userService;
        this.quizHistoryService = quizHistoryService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) {
        UserEntity user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserEntity> postUser(@RequestBody UserDto userDto) {
        UserEntity user = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        UserEntity updatedUser = userService.updateUser(id, userDto);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create-user-and-history")
    public ResponseEntity<String> createUserAndHistory() {
        // Créer un nouvel utilisateur
        UserEntity user = new UserEntity();
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");
        userService.createUser(new UserDto());

        // Créer un nouvel historique de quiz avec l'utilisateur sauvegardé
        QuizHistory quizHistory = new QuizHistory();
        quizHistory.setUser(user);
        // Définir d'autres propriétés de l'historique de quiz
        quizHistoryService.createQuizHistory(quizHistory);

        return ResponseEntity.ok("Utilisateur et historique de quiz créés avec succès.");
    }
}
