package com.ood.project.TrelloClone.repository;

import com.ood.project.TrelloClone.model.enitity.Task;
import com.ood.project.TrelloClone.model.enitity.TaskComment;
import com.ood.project.TrelloClone.model.enitity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {

    List<TaskComment> findByTask(Task task);

    List<TaskComment> findByUserDetails(UserDetails userDetails);

    @Override
    TaskComment save(TaskComment taskComment);
}
