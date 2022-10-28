package com.ood.project.TrelloClone.service;

import com.ood.project.TrelloClone.model.enitity.Task;
import com.ood.project.TrelloClone.model.enitity.TaskComment;
import com.ood.project.TrelloClone.model.enitity.TaskHistoryTable;
import com.ood.project.TrelloClone.model.enitity.TaskUsers;
import com.ood.project.TrelloClone.model.task.AddCommentRequest;
import com.ood.project.TrelloClone.model.task.AddUserRequest;
import com.ood.project.TrelloClone.model.task.ModifyTask;
import com.ood.project.TrelloClone.model.task.TaskResponse;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    Task getTask(long taskID);
    List<TaskResponse> getAllTask();
    TaskResponse addComment(AddCommentRequest addCommentRequest);
    TaskResponse addUsers(AddUserRequest addUserRequest);
    Task modifyTask(ModifyTask modifyTask);
    List<TaskComment> getComments(long taskID);
    List<TaskUsers> getTaskUsers(long taskID);
    List<TaskHistoryTable> getHistoryTable(long taskID);
}
