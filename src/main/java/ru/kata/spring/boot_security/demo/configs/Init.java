package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public Init(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /////Юзер и админ для теста приложения
    @PostConstruct
    public void initData() {

        Role roleUser = roleRepository.findByName("ROLE_USER");
        if (roleUser == null) {
            roleUser = new Role(null, "ROLE_USER");
            roleRepository.save(roleUser);
        }

        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        if (roleAdmin == null) {
            roleAdmin = new Role(null, "ROLE_ADMIN");
            roleRepository.save(roleAdmin);
        }


        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setName("admin1");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setAge((byte) 30);
            admin.setLastName("admin1");
            Set<Role> roles = new HashSet<>();
            roles.add(roleAdmin);
            admin.setRoles(roles);
            userRepository.save(admin);
        }


        if (userRepository.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setName("user1");
            user.setAge((byte) 30);
            user.setLastName("user1");
            user.setPassword(passwordEncoder.encode("user"));
            Set<Role> roles = new HashSet<>();
            roles.add(roleUser);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}