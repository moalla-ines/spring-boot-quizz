package com.example.demo.Authentication;

import com.example.demo.Config.AuthenticationResponse;
import com.example.demo.Config.JwtService;
import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserDto;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserDto request){
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody LoginDto request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @PostMapping("/send-verification-email")
    public ResponseEntity<?> sendVerificationEmail(@RequestBody String email) {
        String token = jwtService.generateVerificationToken(email);

        return ResponseEntity.ok("Email de vérification envoyé avec succès");
    }

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        String email = jwtService.extractEmailFromToken(token);
        // Vérifiez si l'email est valide
        // Mettez à jour le statut de vérification de l'utilisateur dans la base de données
        return ResponseEntity.ok("Email vérifié avec succès");
    }
}