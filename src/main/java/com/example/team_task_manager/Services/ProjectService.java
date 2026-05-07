package com.example.team_task_manager.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.team_task_manager.Models.ProjectSummary;
import com.example.team_task_manager.Models.Projects;
import com.example.team_task_manager.Models.Users;
import com.example.team_task_manager.Repository.ProjectRepository;
import com.example.team_task_manager.Repository.UserRepository;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository repository;
    @Autowired
    UserRepository userRepository;
    public List<Projects> getAll(){
        return repository.findAll();
    }
    public List<String> getprojectNames(){
        return repository.projectNames();
    }
    public void createProject(String name, String description, String userName){
        Users user = userRepository.findByUserName(userName)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with user name: " + userName));
        Projects project = new Projects(name,description,user);
        repository.save(project);
    }
    public List<ProjectSummary> getProjectSummary(){
        List<Projects> projects = repository.findAll();
        List<ProjectSummary> list = new ArrayList<>();
        for(Projects project : projects){
            String user = project.getCreatedBy().getUserName();
            ProjectSummary summary = new ProjectSummary(project.getId(), project.getName(), project.getDescription(), user);
            list.add(summary);
        }
        return list;
    }
    public Projects getProjectById(Long id){
        Projects project = repository.findById(id).orElseThrow(()->new RuntimeException("Project not found"));
        return project;
    }
    public void updateProject(Long id, String title, String description){
        Projects project = repository.findById(id).orElseThrow(()->new RuntimeException("Project not found"));
        project.setName(title); 
        project.setDescription(description);
        repository.save(project);
    }
}
