package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "roleIds", required = false) List<Long> roleIds) {
        if (roleIds == null) {
            roleIds = new ArrayList<>();
        }
        userServiceInterface.updateUser(user, roleIds);
        return "redirect:/admin/all-users";
    }
    @PostMapping("/admin/save-new-user")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("roleIds") List<Long> roleIds) {
        userServiceInterface.saveUser(user, roleIds);
        return "redirect:/admin/all-users";
    }

    @GetMapping("/admin/all-users")
    public List<User> ShowUsers() {
        return userServiceInterface.allUsers();
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userServiceInterface.deleteUser(id);
        return "redirect:/admin/all-users";
    }
}
