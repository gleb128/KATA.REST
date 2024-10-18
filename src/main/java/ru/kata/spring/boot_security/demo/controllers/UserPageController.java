package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserServiceInterface;

@Controller
public class UserPageController {
    @Autowired
    private UserServiceInterface userService;

    @GetMapping("/user")
    public String userInfo() {
        return "User_Info";
    }
}