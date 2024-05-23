package com.example.demo.Controller;

import com.example.demo.Authentication.Token;
import com.example.demo.Entity.PasswordResetToken;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.PasswordResetTokenRepository;
import com.example.demo.Repository.TokenRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.SystemEventListener;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@RestController
@RequestMapping("/api/v1/reset-password")
@CrossOrigin( )
public class PasswordResetController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;


    private PasswordEncoder passwordEncoder;


    @Autowired
    public PasswordResetController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("")
    public ResponseEntity<String> resetPassword(@RequestParam("email") String userEmail, HttpServletRequest request) {

        Optional<UserEntity> userOpt = userRepository.findByEmail(userEmail);

        if (userOpt.isEmpty()) {
            return new ResponseEntity<>("Adresse e-mail non trouvée.", HttpStatus.NOT_FOUND);
        }
        UserEntity user = userOpt.get();
        int min = 100000;
        int max = 999999;
        int mytoken = ThreadLocalRandom.current().nextInt(min, max + 1);
        String token = String.valueOf(mytoken);
        System.out.println("Token: "+token);
        userService.createPasswordResetTokenForUser(user, token);

        userService.sendResetTokenEmail( token, user);

        return new ResponseEntity<>("Password reset link sent to your email" , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<String> showResetPasswordPage(@RequestParam("token") String token) {

        Optional<PasswordResetToken> resetTokenOpt = Optional.ofNullable(tokenRepository.findByToken(token));
        if (resetTokenOpt.isEmpty() || resetTokenOpt.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            return new ResponseEntity<>("Token invalide ou expiré", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Token valide, procédez à la réinitialisation du mot de passe.", HttpStatus.OK);
    }

    @PostMapping("/change-Password")
    public ResponseEntity<String> savePassword(@RequestParam("token") String token, @RequestParam("newPassword") String password) {
        Optional<PasswordResetToken> resetTokenOpt = Optional.ofNullable(tokenRepository.findByToken(token));
        if (resetTokenOpt.isEmpty() || resetTokenOpt.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            return new ResponseEntity<>("Token invalide ou expiré", HttpStatus.BAD_REQUEST);
        }
        System.out.println("aa"+password);

        UserEntity user = resetTokenOpt.get().getUser();
        System.out.println("aa"+user);
        user.setPassword(passwordEncoder.encode(password)); // Encode the password
        userRepository.save(user);
        return new ResponseEntity<>("Mot de passe réinitialisé avec succès.", HttpStatus.OK);
    }
}

