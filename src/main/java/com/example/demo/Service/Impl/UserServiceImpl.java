package com.example.demo.Service.Impl;

import com.example.demo.Config.UnauthorizedException;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.PasswordResetToken;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.PasswordResetTokenRepository;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.TypeUserRepository;
import com.example.demo.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    private JavaMailSender mailSender;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository,JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.mailSender = mailSender;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findByExiste(1);
    }

    @Override
    public UserEntity getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity createUser(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        // Initialiser la liste des rôles de l'utilisateur
        user.setRoles(new ArrayList<>());

        // Récupérer le rôle correspondant à l'ID 2
        Role userRole = roleRepository.findById(2).orElse(null);
        if (userRole == null) {
            throw new RuntimeException("Le rôle avec l'ID 2 n'a pas été trouvé.");
        }

        // Ajouter le rôle à la liste des rôles de l'utilisateur
        user.getRoles().add(userRole);

        return userRepository.save(user);
    }

    public void createPasswordResetTokenForUser(UserEntity user, String token) {
        // Check if a token for this user already exists and delete it
        PasswordResetToken existingToken = tokenRepository.findByUser(user);

        if (existingToken != null) {
            tokenRepository.delete(existingToken);
        }

        // Create and save a new token
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        System.out.println("myToken "+myToken);
        tokenRepository.save(myToken);
    }

    public void sendResetTokenEmail(String token, UserEntity user) {
        System.out.println(token);

        String subject = "Demande de réinitialisation de mot de passe";
        String senderName = "CODE CRAFTER";
        String url ="http://localhost:8080/api/v1/reset-password?token=" + token;
        String mailContent = "<p>Cher " + user.getEmail() + ",</p>";
        mailContent += "<p>Vous trouverez ce code ci-dessous pour réinitialiser votre mot de passe :</p>";
        mailContent += "<p CODE: </p>"+token+" " ;
        mailContent += "<p>Merci,<br>CODE CRAFTER</p>";
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("eshopcommerce11@gmail.com", senderName);
            helper.setTo(user.getUsername());
            helper.setSubject(subject);
            helper.setText(mailContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void updateUserPassword(UserEntity user, String newPassword, String token) {
        // Vérifie si le token est valide
        if (isValidToken(token)) {
            // Encode le nouveau mot de passe avant de le sauvegarder

            String encodedPassword = passwordEncoder.encode(newPassword);
            System.out.println(newPassword);
            System.out.println(encodedPassword);
            System.out.println(user.getPassword());
            user.setPassword(encodedPassword);
            // Sauvegarde les modifications de l'utilisateur
            userRepository.save(user);
        } else {
            // Lance une exception si le token est invalide ou manquant
            throw new UnauthorizedException("Invalid or missing token");
        }
    }

    public void deleteUserAndRelatedEntities(Integer id) throws ChangeSetPersister.NotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        user.setExiste(0); // Mettre à jour la propriété existe à 0
        userRepository.save(user); // Enregistrer les modifications dans la base de données
    }


    @Override
    public UserEntity updateUserRole(Integer id, Optional<Role> role) {
        return null;
    }

    @Override
    public UserEntity updateUserRole(Integer id, String roleName) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user != null) {
            Role role = roleRepository.findByName(roleName).orElse(null);
            if (role != null) {
                // Mettre à jour le rôle de l'utilisateur
                user.setRoles(new ArrayList<>());
                user.getRoles().add(role);
                return userRepository.save(user);
            }
        }
        return null;
    }

    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }


    private boolean isValidToken(String token) {
        System.out.println(token);
        // Vérifie si le token n'est ni nul ni vide (exemple simplifié)
        return token != null && !token.isEmpty();

    }

}