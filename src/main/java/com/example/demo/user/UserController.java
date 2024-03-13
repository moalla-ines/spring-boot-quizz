package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("api/v1/acceuil_view")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @Service
    public class UserService {
        private final UserRepository userRepository;

        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        public User getUserById(Long id) {
            return userRepository.findById(id).orElse(null);
        }

        public User createUser(User user) {
            return userRepository.save(user);
        }
        public User updateUser(Long id, User newUser) {
            User existingUser = userRepository.findById(id).orElse(null);
            if (existingUser != null) {
                existingUser.setFirstname(newUser.getFirstname());
                existingUser.setLastname(newUser.getLastname());
                existingUser.setEmail(newUser.getEmail());
                existingUser.setDob(newUser.getDob());
                existingUser.setAge(newUser.getAge());
                return userRepository.save(existingUser);
            }
            return null;
        }

        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }
    }}