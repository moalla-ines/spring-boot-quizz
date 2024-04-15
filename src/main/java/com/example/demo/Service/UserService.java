package com.example.demo.Service;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity getUserById(Integer id);

    UserEntity createUser(UserDto user);

    UserEntity updateUser(Integer id, UserDto newUser);

    void deleteUser(Integer id);



    void updateUserPassword(UserEntity user, String newPassword);
}