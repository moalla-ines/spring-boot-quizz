package com.example.demo.Service;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.UserEntity;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity getUserById(Integer id);

    UserEntity createUser(UserDto user);





    void updateUserPassword(UserEntity user, String newPassword, String token
    );

    void deleteUserAndRelatedEntities(Integer id) throws ChangeSetPersister.NotFoundException;

    UserEntity updateUserRole(Integer id, Optional<Role> role);

    UserEntity updateUserRole(Integer id, String roleName);
    void createPasswordResetTokenForUser(UserEntity user, String token);
    void sendResetTokenEmail(String token, UserEntity user);
}