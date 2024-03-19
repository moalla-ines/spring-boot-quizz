package com.example.demo.Controller;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Response.LoginResponse;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PostMapping()
    public ResponseEntity<User> postUsers(@RequestBody User user){
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto){
        // Vérifier si le rôle est spécifié, sinon utiliser "user" par défaut
        String role = userDto.getRole() != null ? userDto.getRole() : "user";
        userDto.setRole(role);

        String id = userService.addUser(userDto);
        return ResponseEntity.ok(id);
    }


    @PostMapping(path = "/auth")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDto loginDto){
        LoginResponse loginResponse = userService.loginUser(loginDto);
        return ResponseEntity.ok(loginResponse);
    }
}
