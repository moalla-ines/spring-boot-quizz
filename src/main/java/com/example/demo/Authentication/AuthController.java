package com.example.demo.Authentication;

import com.example.demo.Config.AuthenticationResponse;
import com.example.demo.Config.JwtService;
import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin( )
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")

    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        System.out.println("hey");
        authService.register(userDto);
        return ResponseEntity.ok("Registration successful. Please check your email for verification.");
    }


    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("token") String token) {
        if (authService.verifyUser(token)) {
            return ResponseEntity.ok("Verification successful. You can now log in.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody LoginDto request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}