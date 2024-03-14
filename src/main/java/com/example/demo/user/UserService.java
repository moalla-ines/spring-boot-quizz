package com.example.demo.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@Service
public class UserService {
    public List<User> GetUsers() {
        return List.of(
                new User(
                        11,
                        "Ines",
                        "12345678",
                        "Inesmoalla68@gmail.com"

                )
        );
    }
}
