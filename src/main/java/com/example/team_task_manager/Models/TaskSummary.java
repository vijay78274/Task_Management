package com.example.team_task_manager.Models;

import java.time.LocalDate;

public class TaskSummary {
    private Long id;
 
    private String title;
    private Status status;
    private LocalDate dueDate;
    private String assignedTo;

    private String project;

    public TaskSummary(Long taskID ,String name, String user, String project2, Status status2, LocalDate dueDate2) {
        this.id=taskID;
        this.title=name;
        this.assignedTo=user;
        this.project=project2;
        this.status=status2;
        this.dueDate=dueDate2;
    }
    public TaskSummary(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
 
}
