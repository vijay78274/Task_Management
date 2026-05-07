package com.example.team_task_manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.team_task_manager.Models.Tasks;

import java.util.List;


public interface TaskRepository extends JpaRepository<Tasks, Long> {

    @Query(value="select t.* from tasks t join users u on t.assigned_to_id=u.id where u.username=?1", nativeQuery = true)
    List<Tasks> findByAssignedTo(String assignedTo);

}