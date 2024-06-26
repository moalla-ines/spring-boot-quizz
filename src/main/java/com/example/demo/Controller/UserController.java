package com.example.demo.Controller;

import com.example.demo.Config.UnauthorizedException;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Service.Impl.RoleService;
import com.example.demo.Service.QuizHistoryService;
import com.example.demo.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin()
public class UserController {

    private final UserService userService;
    private final QuizHistoryService quizHistoryService;
    private final PasswordEncoder passwordEncoder;
    private  final RoleService roleService;

    @Autowired
    public UserController(UserService userService, QuizHistoryService quizHistoryService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.quizHistoryService = quizHistoryService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
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
    @PutMapping("/{id}/password")
    public ResponseEntity<Map<String, String>> updateUserPassword(@PathVariable Integer id, @RequestBody String newPassword, @RequestHeader("Authorization") String token) {
        Optional<UserEntity> optionalUser = Optional.ofNullable(userService.getUserById(id));
System.out.println(optionalUser);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            System.out.println(user);
            try {
                userService.updateUserPassword(user, newPassword, token);
                return ResponseEntity.ok(Collections.singletonMap("message", "Password updated successfully"));
            } catch (UnauthorizedException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Unauthorized"));
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestParam String name) {
        Optional<Role> role = roleService.findRoleByName(name); // Assuming you have a method in your service to find a role by name
        if (role == null) {
            return ResponseEntity.notFound().build();
        }

        UserEntity updatedUser = userService.updateUserRole(id, role);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUserAndRelatedEntities(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Gérer l'erreur de suppression
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }}


