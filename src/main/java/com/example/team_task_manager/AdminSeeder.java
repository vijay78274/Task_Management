package com.example.team_task_manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.team_task_manager.Models.Role;
import com.example.team_task_manager.Models.Users;
import com.example.team_task_manager.Repository.UserRepository;

@Configuration
public class AdminSeeder {

    @Bean
    public CommandLineRunner createAdmin(UserRepository userRepository,
                                         PasswordEncoder passwordEncoder) {

        return args -> {

            String adminName = System.getenv("ADMIN_USERNAME");
            String password = System.getenv("ADMIN_PASSWORD");

            if (adminName == null || password == null) return;

            if (userRepository.findByUserName(adminName).isEmpty()) {

                Users admin = new Users();
                admin.setName("Admin");
                admin.setUserName(adminName);

                admin.setPassword(passwordEncoder.encode(password));

                admin.setRole(Role.ADMIN);

                userRepository.save(admin);

                System.out.println("Admin created successfully");
            } else {
                System.out.println("ℹAdmin already exists");
            }
        };
    }
}
