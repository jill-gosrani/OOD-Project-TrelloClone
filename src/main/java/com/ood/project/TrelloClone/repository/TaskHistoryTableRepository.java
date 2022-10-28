package com.ood.project.TrelloClone.repository;

import com.ood.project.TrelloClone.model.TaskHistoryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskHistoryTableRepository extends JpaRepository<TaskHistoryTable, Long> {

    List<TaskHistoryTable> findByTaskID(long taskID);
    @Override
    TaskHistoryTable save(TaskHistoryTable taskHistoryTable);
}
