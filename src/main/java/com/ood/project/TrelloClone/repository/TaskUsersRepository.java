package com.ood.project.TrelloClone.repository;

import com.ood.project.TrelloClone.model.enitity.Task;
import com.ood.project.TrelloClone.model.enitity.TaskUsers;
import com.ood.project.TrelloClone.model.enitity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskUsersRepository extends JpaRepository<TaskUsers, Long> {

    List<TaskUsers> findByTask(Task task);

    List<TaskUsers> findByUserDetails(UserDetails userDetails);

    @Override
    TaskUsers save(TaskUsers taskUsers);
}
//TODO: UNDO, HISTORY, Multiple Boards