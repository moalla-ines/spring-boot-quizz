package com.example.demo.Service;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Response.LoginResponse;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Integer id);

    User createUser(User user);

    User updateUser(Integer id, User newUser);

    void deleteUser(Integer id);

    String addUser(UserDto userDto);

    LoginResponse loginUser(LoginDto loginDto);
}
