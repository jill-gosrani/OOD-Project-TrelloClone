package com.ood.project.TrelloClone.repository;

import com.ood.project.TrelloClone.model.TaskUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskUsersRepository extends JpaRepository<TaskUsers, Long> {

    List<TaskUsers> findByTaskID(long taskID);
    TaskUsers findByUserID(long userID);
    @Override
    TaskUsers save(TaskUsers taskUsers);
}
//TODO: UNDO, HISTORY, Multiple Boards