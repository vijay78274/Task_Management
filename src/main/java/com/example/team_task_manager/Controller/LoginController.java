package com.example.team_task_manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.team_task_manager.MyUserDetails;
import com.example.team_task_manager.Services.UserService;
import com.example.team_task_manager.Models.Role;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired 
    public UserService service;
    
   
    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }
    @GetMapping("/signup")
    public String signupPage(){
        return "Signup";
    }
    @PostMapping("/create")
    public String creteUser(@RequestParam String userName, @RequestParam String name, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        if(password.equals(confirmPassword)){
            service.saveUser(name, userName, password, confirmPassword);
            System.out.println("username: "+userName);
            return "Login";
        }
        else{
            model.addAttribute("message", "Confirm password doesn't match original password");
            return "Signup";
        }
    }
    @GetMapping("/home")
    public String homePage(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()
        || authentication.getPrincipal().equals("anonymousUser")) {
        return "redirect:/login";
        }
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Role role = userDetails.getRole();
        System.out.println(role);
        if (role.equals(Role.ADMIN)) {
            System.out.println("Admin Dashboard");
            return "redirect:/admin/tasks";
        } else {
            return "redirect:/member/tasks";
        }
    }
}
