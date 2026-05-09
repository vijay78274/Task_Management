package com.example.team_task_manager.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.team_task_manager.Models.Projects;
import com.example.team_task_manager.Models.Status;
import com.example.team_task_manager.Models.TaskSummary;
import com.example.team_task_manager.Models.Tasks;
import com.example.team_task_manager.Models.Users;
import com.example.team_task_manager.Repository.ProjectRepository;
import com.example.team_task_manager.Repository.TaskRepository;
import com.example.team_task_manager.Repository.UserRepository;

@Service
public class TaskService {
    @Autowired
    TaskRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    public List<Tasks> getAll(){
        return repository.findAll(); 
    }

    public void createTask(String name, String userID, String projectName, String dueDate){
        Users user = userRepository.findByUserName(userID)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with user name: " + userID));
        Projects project = projectRepository.findByName(projectName);
        Tasks task = new Tasks(name, user, project, Status.PENDING, LocalDate.parse(dueDate));
        repository.save(task);
    }

    public List<TaskSummary> getTaskSummaries(String status, String project, String username, Boolean overdue){
        
        List<Tasks> tasks = repository.filterTasks(
            status,
            project,
            username,
            overdue
        );
        List<TaskSummary> list = new ArrayList<>();
        for(Tasks task: tasks){
            String userName = task.getAssignedTo().getUserName();
            String projectName = task.getProject().getName();
            TaskSummary summary = new TaskSummary(task.getId(),task.getTitle(), userName, projectName, task.getStatus(), task.getDueDate());
            list.add(summary);
        }
        return list;
    }

    public List<TaskSummary> getTaskSummariesByUser(String userName){
        List<Tasks> tasks = repository.findByAssignedTo(userName);
        List<TaskSummary> list = new ArrayList<>();
        for(Tasks task: tasks){
            String user = task.getAssignedTo().getUserName();
            String projectName = task.getProject().getName();
            TaskSummary summary = new TaskSummary(task.getId(),task.getTitle(), user, projectName, task.getStatus(), task.getDueDate());
            list.add(summary);
        }
        return list;
    }
    public TaskSummary getTaskSummaryById(Long taskId){
        Tasks task = repository.findById(taskId).orElseThrow(()->new RuntimeException("Task not found"));
        TaskSummary summary = new TaskSummary(task.getId(),task.getTitle(), task.getAssignedTo().getUserName(), task.getProject().getName(), task.getStatus(), task.getDueDate());
        return summary;
    }
    public void updateTask(Long taskID, String title, String projectName, String assignedTo, String dueDate, Status status){
        Tasks task = repository.findById(taskID).orElseThrow(()->new RuntimeException("Task not found"));
        Projects project = projectRepository.findByName(projectName);
        Users user = userRepository.findByUserName(assignedTo).orElseThrow(()->new RuntimeException("User not found"));
        task.setTitle(title);
        task.setProject(project);
        task.setAssignedTo(user);
        task.setDueDate(LocalDate.parse(dueDate));
        task.setStatus(status);
        repository.save(task);
    }
    public void deleteTask(Long taskId){
        repository.deleteById(taskId);
    }
}
