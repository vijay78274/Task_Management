package com.example.team_task_manager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.team_task_manager.MyUserDetails;
import com.example.team_task_manager.Models.Role;
import com.example.team_task_manager.Models.Status;
import com.example.team_task_manager.Models.TaskSummary;
import com.example.team_task_manager.Services.ProjectService;
import com.example.team_task_manager.Services.TaskService;
import com.example.team_task_manager.Services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    TaskService taskService;
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;
    @GetMapping("/tasks")
    public String showTasks(Model model, @RequestParam(required = false) String status,

    @RequestParam(required = false) String projectName,

    @RequestParam(required = false) String userName,

    @RequestParam(required = false) Boolean overdue) {
        List<TaskSummary> tasks = taskService.getTaskSummaries(
            status,
            projectName,
            userName,
            overdue
        );
        model.addAttribute("projects", projectService.getprojectNames());
        model.addAttribute("users", userService.getUserNames());
        model.addAttribute("tasks",tasks);
        return "admin-dashboard"; 
    }
    @GetMapping("/projects")
    public String showProjects(Model model) {
        model.addAttribute("projects",projectService.getProjectSummary());
        return "admin-projects"; 
    }
    @GetMapping("/create_project")
    public String createProjectPage() {
        return "create-project";
    }
    
    @PostMapping("/create_project")
    public String createProject(@RequestParam String title, @RequestParam String description, Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        projectService.createProject(title, description, userName);
        return "redirect:/admin/projects";
    }
    @GetMapping("/update_project/{id}")
    public String updateProjectPage(@PathVariable Long id, Model model) {
        model.addAttribute("project",projectService.getProjectById(id));
        return "update-project";
    }
    
    @PostMapping("/update_project/{id}")
    public String updateProject(@PathVariable Long id, @RequestParam String title, @RequestParam String description) {
        projectService.updateProject(id, title, description);
        return "redirect:/admin/projects";
    }
    @GetMapping("/update_task/{id}")
    public String updateTaskPage(@PathVariable Long id, Model model) {
        TaskSummary summary = taskService.getTaskSummaryById(id);
        model.addAttribute("projects",projectService.getprojectNames());
        model.addAttribute("task",summary);
        model.addAttribute("users", userService.getUserNames());
        return "update-task";
    }
    
    @PostMapping("/update_task/{id}")
    public String updatTask(@PathVariable Long id, @RequestParam String title, @RequestParam Status status, @RequestParam String assignedTo, @RequestParam String dueDate, @RequestParam String project) {
        taskService.updateTask(id, title, project, assignedTo, dueDate, status);
        return "redirect:/admin/tasks";
    }
    @GetMapping("/create_task")
    public String createTaskPage(Model model) {
        model.addAttribute("projects",projectService.getprojectNames());
        model.addAttribute("users", userService.getUserNames());
        return "create-task";
    }
    
    @PostMapping("/create_task")
    public String createTask(@RequestParam String title, @RequestParam String assignedTo, @RequestParam String dueDate, @RequestParam String project) {
            taskService.createTask(title, assignedTo, project, dueDate);
            return "redirect:/admin/tasks";
    }
    @PostMapping("/delete_task/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/admin/tasks";
    }
    @PostMapping("/delete_project/{id}")
    public String deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return "redirect:/admin/projects";
    }
    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users",userService.getAll());
        return "admin-users"; 
    }
    @GetMapping("/update_user/{id}")
    public String updateUserPage(@PathVariable Long id, Model model) {
        model.addAttribute("user",userService.getUserById(id));
        return "update-user";
    }
    @PostMapping("/update_user/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam Role role) {
        userService.updateUser(id, name, role);
        return "redirect:/admin/users";
    }
    @PostMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
