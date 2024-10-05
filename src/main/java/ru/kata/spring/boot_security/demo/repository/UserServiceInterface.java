package ru.kata.spring.boot_security.demo.repository;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserServiceInterface {
    void updateUser(User user, List<Long> roleIds);

    UserDetails loadUserByUsername(String username);

    User findUserById(Long userId);

    List<User> allUsers();

    boolean saveUser(User user, List<Long> roleIds);

    boolean deleteUser(Long userId);
}
