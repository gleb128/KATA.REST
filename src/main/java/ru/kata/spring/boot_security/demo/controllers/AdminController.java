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

@RestController
public class AdminController {
    @Autowired
    private UserServiceInterface userServiceInterface;

    @PutMapping("/admin/update-user")
    public ResponseEntity<User> updateUser(@RequestParam(value = "roleIds", required = false) List<Long> roleIds, @RequestBody User user) {
        if (roleIds == null) {
            roleIds = new ArrayList<>();
        }
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        userServiceInterface.updateUser(user, roleIds);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/admin/save-new-user")
    public ResponseEntity<String> saveUser(@RequestBody User user, @RequestParam("roleIds") List<Long> roleIds) {
        boolean isSaved = userServiceInterface.saveUser(user, roleIds);
        if (!isSaved) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("null");
        }
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @GetMapping("/admin/all-users")
    public ResponseEntity<List<User>> ShowUsers() {
        List<User> users = userServiceInterface.allUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<String> deleteUser(@RequestParam("id") Long id) {
        userServiceInterface.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
}
