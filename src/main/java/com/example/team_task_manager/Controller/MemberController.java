package com.example.team_task_manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.team_task_manager.MyUserDetails;
import com.example.team_task_manager.Services.ProjectService;
import com.example.team_task_manager.Services.TaskService;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/tasks")
    public String showTasks(Model model,Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        model.addAttribute("tasks",taskService.getTaskSummariesByUser(userName));
        return "member-dashboard"; 
    }
    @GetMapping("/projects")
    public String showProjects(Model model) {
        model.addAttribute("projects",projectService.getProjectSummary());
        return "member-projects"; 
    }
}
