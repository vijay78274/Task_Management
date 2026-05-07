package com.example.team_task_manager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig { 
    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable()) 
        .authorizeHttpRequests(request -> 
            request
                .requestMatchers("/login","/css/**", "/js/**", "/signup","/create").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/public/**").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated() 
        )
        .formLogin(form -> 
            form
                .loginPage("/login") 
                .loginProcessingUrl("/perform_login") 
                .defaultSuccessUrl("/home", true) 
                .failureUrl("/login?error=true") 
                .permitAll() 
        )
        .logout(logout -> 
            logout
                .logoutUrl("/logout") 
                .logoutSuccessUrl("/login?logout=true") 
                .permitAll()
        )
        .build();
}

    @Bean 
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
