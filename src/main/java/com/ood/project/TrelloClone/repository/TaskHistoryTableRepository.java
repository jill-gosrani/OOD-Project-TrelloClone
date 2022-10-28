package com.ood.project.TrelloClone.repository;

import com.ood.project.TrelloClone.model.enitity.Task;
import com.ood.project.TrelloClone.model.enitity.TaskHistoryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskHistoryTableRepository extends JpaRepository<TaskHistoryTable, Long> {

    List<TaskHistoryTable> findByTask(Task task);
    @Override
    TaskHistoryTable save(TaskHistoryTable taskHistoryTable);
}
