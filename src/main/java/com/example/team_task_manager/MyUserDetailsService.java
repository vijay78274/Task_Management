package com.example.team_task_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.team_task_manager.Models.Users;
import com.example.team_task_manager.Repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    public UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {        
        if (username == null || username.isBlank()) {
            System.out.println("User");
            throw new UsernameNotFoundException("Username is empty");
        }
        Users user = repository.findByUserName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with user name: " + username));
        System.out.println("User found: " + user.getUserName());
        return new MyUserDetails(user);
    }

    public Users save(Users user){
        return repository.save(user);
    }
}