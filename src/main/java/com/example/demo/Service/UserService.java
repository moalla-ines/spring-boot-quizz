package com.example.demo.Service;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Response.LoginResponse;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity getUserById(Integer id);

    UserEntity createUser(UserDto user);

    UserEntity updateUser(Integer id, UserDto newUser);

    void deleteUser(Integer id);

}