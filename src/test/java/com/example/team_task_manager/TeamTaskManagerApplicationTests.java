package com.example.team_task_manager;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.team_task_manager.Models.Role;
import com.example.team_task_manager.Models.Users;
import com.example.team_task_manager.Repository.UserRepository;
import com.example.team_task_manager.Services.ProjectService;
import com.example.team_task_manager.Services.TaskService;

@SpringBootTest
class TeamTaskManagerApplicationTests {
	 @Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository repository;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;
	@Test
	void contextLoads() {
	}
	
	@Test
	void createUser(){
		String encryptedPassword = passwordEncoder.encode("12345");
		Users user = new Users("Neeraj", "admin123", encryptedPassword, Role.ADMIN);
		Users user1 = new Users("Vijay", "vijay328", encryptedPassword, Role.MEMBER);
		Users user2 = new Users("Rakesh", "rakesh982", encryptedPassword, Role.MEMBER);
		Users user3 = new Users("Vipin", "vipin435", encryptedPassword, Role.MEMBER);
		Users user4 = new Users("Mohit", "mohit234", encryptedPassword, Role.MEMBER);
		repository.save(user);
        repository.save(user1);
		repository.save(user2);
		repository.save(user3);
		repository.save(user4);
	}
	@Test
	public void insertProject(){
		projectService.createProject("BookCab App", "Cab booking android application for employees", "admin123");
		projectService.createProject("Ignite DashBoard", "Company career website for students", "admin123");
		projectService.createProject("AI data analyzer", "Tool to enhace analysis process", "admin123");
	}
	@Test
	public void insertTask(){
		taskService.createTask("Create Models and Service Logic","vijay328", "BookCab App","2026-06-05");
		taskService.createTask("Create payment function","vipin435", "BookCab App","2026-06-05");
		taskService.createTask("AI data analyzer","rakesh982", "Ignite Dashboard","2026-07-06");
	}
}
