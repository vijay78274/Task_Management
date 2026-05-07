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
    public void updateUser(Long id, Users user){
        Users oldUser = new Users();
        oldUser.setName(user.getName());
        userRepository.save(oldUser);
    }
    public List<String> getUserNames(){
        return userRepository.userNames();
    }
}
