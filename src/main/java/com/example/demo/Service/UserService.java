package com.example.demo.Service;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Response.LoginResponse;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity getUserById(Integer id);

    UserEntity createUser(UserEntity user);

    UserEntity updateUser(Integer id, UserEntity newUser);

    void deleteUser(Integer id);

    String addUser(UserDto userDto);

    LoginResponse loginUser(LoginDto loginDto);
}
