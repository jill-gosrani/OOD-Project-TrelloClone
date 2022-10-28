package com.ood.project.TrelloClone.service;

import com.ood.project.TrelloClone.model.*;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    Task getTask(long taskID);
    List<Task> getAllTask();
    Task addComment(TaskComment taskComment);
    Task addUsers(TaskUsers taskUsers);
    Task modifyTask(ModifyTask task);
    List<TaskComment> getComments(long taskID);
    List<TaskUsers> getTaskUsers(long taskID);
    List<TaskHistoryTable> getHistoryTable(long taskID);
}
