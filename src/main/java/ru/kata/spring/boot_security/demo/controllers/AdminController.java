package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserServiceInterface;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class AdminController {
    @Autowired
    private UserServiceInterface userServiceInterface;

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        userServiceInterface.updateUser(user);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/admin/users")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        boolean isSaved = userServiceInterface.saveUser(user);
        if (!isSaved) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("null");
        }
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> ShowUsers() {
        List<User> users = userServiceInterface.allUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/admin/user-info/{id}")
    public ResponseEntity<User> getUserInfo(@PathVariable Long id)  {
        User user = userServiceInterface.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<String> deleteUser( @PathVariable Long id) {
        userServiceInterface.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
}
