package com.example.team_task_manager.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.team_task_manager.Models.Role;
import com.example.team_task_manager.Models.Users;
import com.example.team_task_manager.Repository.UserRepository;

@Service
public class UserService {
    @Autowired 
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<Users> getAll(){
        return userRepository.findAll();
    }
    public void saveUser(String name, String email, String password, String confimPassword){
        String encryptedPassword = passwordEncoder.encode(password);
        Users user = new Users(name, email, encryptedPassword, Role.MEMBER);
        System.out.println("User saved");
        userRepository.save(user);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    public void updateUser(Long id, String name, Role role){
        Users oldUser = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        oldUser.setName(name);
        oldUser.setRole(role);
        userRepository.save(oldUser);
    }
    public List<String> getUserNames(){
        return userRepository.userNames();
    }
    public Users getUserById(Long id){
        Users user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        return user;
    }
}
