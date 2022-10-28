package com.ood.project.TrelloClone.repository;

import com.ood.project.TrelloClone.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTaskID(long taskID);
    @Override
    Task save(Task task);
    List<Task> findByOrderByStatusAsc();
}
