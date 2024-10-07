package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceInterface;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class AdminContoller {
    @Autowired
    private UserServiceInterface userServiceInterface;

    @GetMapping("/admin/new-user")
    public String newUser(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        User user = new User();
        user.setRoles(new HashSet<>());
        model.addAttribute("user", user);
        List<Role> roles = userServiceInterface.findAll();
        model.addAttribute("roles", userServiceInterface.findAll());
        return "user_form";
    }

    @PostMapping("/admin/update-user")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "roleIds", required = false) List<Long> roleIds) {
        if (roleIds == null) {
            roleIds = new ArrayList<>();
        }
        userServiceInterface.updateUser(user, roleIds);
        return "redirect:/admin/all-users";
    }

    @PostMapping("/admin/save-new-user")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam("roleIds") List<Long> roleIds) {
        userServiceInterface.saveUser(user, roleIds);
        return "redirect:/admin/all-users";
    }


    @GetMapping("/admin/edit")
    public String editUser(@RequestParam(name = "id") Long id, Model model) {
        User user = userServiceInterface.findUserById(id);
        model.addAttribute("user", user);
        List<Role> roles = userServiceInterface.findAll();
        model.addAttribute("roles", roles);
        return "user_form_edit";
    }

    @GetMapping("/admin/all-users")
    public String ShowUsers(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        List<User> users = userServiceInterface.allUsers();
        List<Role> roles = (List<Role>) userServiceInterface.findAll();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "ShowUsers";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userServiceInterface.deleteUser(id);
        return "redirect:/admin/all-users";
    }


}
