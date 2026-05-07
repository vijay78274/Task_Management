package com.example.team_task_manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.team_task_manager.MyUserDetails;
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
    public String showTasks(Model model) {
        model.addAttribute("tasks",taskService.getTaskSummaries());
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
}
