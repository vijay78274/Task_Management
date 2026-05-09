package com.example.team_task_manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.team_task_manager.Models.Tasks;

import java.util.List;


public interface TaskRepository extends JpaRepository<Tasks, Long> {

    @Query(value="select t.* from tasks t join users u on t.assigned_to_id=u.id where u.username=?1", nativeQuery = true)
    List<Tasks> findByAssignedTo(String assignedTo);

    @Query(value="SELECT t.* FROM tasks t join users u on t.assigned_to_id=u.id join projects p on t.project_project_id = p.project_id WHERE (?1 IS NULL OR ?1 = '' OR t.status = ?1) AND (?2 IS NULL OR ?2 = '' OR p.name = ?2) AND (?3 IS NULL OR ?3 = '' OR u.username = ?3) AND (?4 IS NULL OR (?4 = true AND t.due_date < CURRENT_DATE AND t.status = 0))", nativeQuery = true)
    List<Tasks> filterTasks(
            String status,
            String project,
            String user,
            Boolean overdue
    );

}