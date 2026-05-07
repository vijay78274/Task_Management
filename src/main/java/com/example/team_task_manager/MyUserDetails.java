package com.example.team_task_manager;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.team_task_manager.Models.Role;
import com.example.team_task_manager.Models.Users;

public class MyUserDetails implements UserDetails{

    Users users;
    public MyUserDetails(Users users) {
        this.users=users;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + users.getRole())); // to dynamically add the role
        return roles;
    }


    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUserName();
    }

    public Role getRole() {
        return users.getRole();
    }
}