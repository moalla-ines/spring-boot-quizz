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
                        1L,
                        "Ines",
                        "Moalla",
                        "",
                        "Inesmoalla68@gmail.com",
                        LocalDate.of(2003, Month.JANUARY, 10),
                        21
                )
        );
    }
}
