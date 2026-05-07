package com.example.team_task_manager.Models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="taskId")
    private Long id;
 
    private String title;
    private Status status;
    private LocalDate dueDate;

    @ManyToOne
    private Users assignedTo;

    @ManyToOne
    private Projects project;

    public Tasks(String name, Users user, Projects project2, Status status2, LocalDate dueDate2) {
        this.title=name;
        this.assignedTo=user;
        this.project=project2;
        this.status=status2;
        this.dueDate=dueDate2;
    }
    public Tasks(){}

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

    public Users getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Users assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
