package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserServiceInterface;

import java.security.Principal;

@RestController
@RequestMapping("/userAPI")
public class UserRestController {
    @Autowired
    private UserServiceInterface userService;

    @GetMapping("/user")
    public ResponseEntity<User> userInfo(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }
}
