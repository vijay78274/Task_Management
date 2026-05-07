package com.example.team_task_manager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.team_task_manager.Models.Projects;

public interface ProjectRepository extends JpaRepository<Projects, Long> {

    @Query(value="select name from projects",nativeQuery = true)
    List<String> projectNames();

    Projects findByName(String name);

}


