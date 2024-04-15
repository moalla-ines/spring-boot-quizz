package com.example.demo.Service.Impl;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
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

    @Override
    public UserEntity updateUser(Integer id, UserDto newUserDto) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();
            if (StringUtils.isNotBlank(newUserDto.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
            }
            if (StringUtils.isNotBlank(newUserDto.getUsername())) {
                existingUser.setUsername(newUserDto.getUsername());
            }
            if (StringUtils.isNotBlank(newUserDto.getEmail())) {
                existingUser.setEmail(newUserDto.getEmail());
            }
            return userRepository.save(existingUser);

        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserPassword(UserEntity user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        System.out.println(user);
    }
}
