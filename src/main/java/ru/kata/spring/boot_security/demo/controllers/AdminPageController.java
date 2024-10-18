package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AdminPageController {

    @GetMapping("/admin/all-users")
    public String showUsersPage() {
        return "ShowUsers";
    }

}

