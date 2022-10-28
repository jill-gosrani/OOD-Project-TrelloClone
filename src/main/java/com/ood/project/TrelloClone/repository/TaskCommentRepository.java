package com.ood.project.TrelloClone.repository;

import com.ood.project.TrelloClone.model.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {

    List<TaskComment> findByTaskID(long taskID);
    @Override
    TaskComment save(TaskComment taskComment);
}
