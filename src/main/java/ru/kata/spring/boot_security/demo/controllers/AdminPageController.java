package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserServiceInterface;
@Controller
public class AdminPageController {
    @Autowired
    private UserServiceInterface userServiceInterface;


    @GetMapping("/admin/all-users")
    public String showUsersPage() {
        return "ShowUsers";
    }

}

