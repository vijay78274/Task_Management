package com.example.team_task_manager.Models;

import jakarta.persistence.*;
@Entity

public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="username", unique = true, nullable = false)
    private String userName;
    @Column(name="password")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Users(){}
    public Users(String name, String userName, String password, Role role){
       this.userName=userName;
       this.name=name;
       this.password=password;
       this.role=role;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
