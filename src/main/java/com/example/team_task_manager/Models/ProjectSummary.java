package com.example.team_task_manager.Models;

public class ProjectSummary {
    private Long id;

    private String name;
    private String description;

    private String createdBy;

    public ProjectSummary(Long id, String name2, String description2, String user) {
        this.id=id;
        this.name=name2;
        this.description=description2;
        this.createdBy=user;
    }
    public ProjectSummary(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
}
