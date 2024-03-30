package com.example.demo.Service.Impl;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.TypeUser;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Response.LoginResponse;
import com.example.demo.Service.UserService;
import com.example.demo.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
private AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, User newUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(newUser.getUsername());
            existingUser.setPassword(newUser.getPassword());
            existingUser.setEmail(newUser.getEmail());

            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public String addUser(UserDto userDto) {
        TypeUser user = new TypeUser(2, "user");
        userDto.setRole(user);

        User user1 = new User(
                userDto.getIduser(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()), // Encode le mot de passe
                userDto.getEmail(),
                userDto.getRole());
        userRepository.save(user1);
        var jwtToken = jwtService.generateToken(userDto);
        return user1.getUsername(); // Renvoie uniquement le nom d'utilisateur
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginDto.getEmail(),
                      loginDto.getPassword()
              )
      );
        Optional<User> user1 = userRepository.findByEmail(loginDto.getEmail());

        if (user1 != null && passwordEncoder.matches(loginDto.getPassword(), user1.getPassword())) { // Vérifie le mot de passe en utilisant le PasswordEncoder
            return new LoginResponse("Login Success", true);
        } else {
            return new LoginResponse("Email or Password incorrect", false); // Message générique pour ne pas divulguer d'informations sur l'email ou le mot de passe incorrect
        }
        var jwtToken = jwtService.generateToken(loginDto);
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }
}
