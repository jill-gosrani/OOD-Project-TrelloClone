package com.ood.project.TrelloClone.service;

import com.ood.project.TrelloClone.model.*;
import com.ood.project.TrelloClone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor

public class TaskServiceImp implements TaskService{
    private final TaskRepository taskRepo;
    private final TaskCommentRepository taskCommentRepo;
    private final TaskUsersRepository taskUsersRepo;
    private final TaskHistoryTableRepository taskHistoryTableRepository;
    private final UserRepository userRepo;
    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String formattedDate = myDateObj.format(myFormatObj);
    List<UserDetails> listOfUsers = new ArrayList<>();
    @Override
    public Task saveTask(Task task) {
        task.setTimeCreated(formattedDate);
        task.setStatus(Status.TODO);
        task.setETC("2 weeks");
        return taskRepo.save(task);
    }

    @Override
    public Task getTask(long taskID) {
        return taskRepo.findByTaskID(taskID);
    }


    @Override
    public List<Task> getAllTask() {
        return taskRepo.findByOrderByStatusAsc();
    }

    @Override
    public Task addComment(TaskComment taskComment) {
        taskCommentRepo.save(taskComment);
        Task task = taskRepo.findByTaskID(taskComment.getTaskID());
        task.setTimeUpdated(formattedDate);
        return taskRepo.save(task);
    }

    @Override
    public Task addUsers(TaskUsers taskUsers) {
        taskUsersRepo.save(taskUsers);
        Task task = taskRepo.findByTaskID(taskUsers.getTaskID());
        task.setTimeUpdated(formattedDate);
        UserDetails user = userRepo.findByUserID(taskUsers.getUserID());
        listOfUsers.add(user);
        task.setUsers(listOfUsers);
        return taskRepo.save(task);
    }

    @Override
    public Task modifyTask(ModifyTask task) {

        Task taskFromRepo = taskRepo.findByTaskID(task.getTaskID());

        if (task.getComment() != null) {
            TaskComment taskComment = new TaskComment();
            taskComment.setTaskID(task.getTaskID());
            taskComment.setComment(task.getComment());
            taskCommentRepo.save(taskComment);
            taskFromRepo.setTaskName(task.getTaskName());
            String modification = "Comment added: " + task.getComment();

            saveToTaskHistory(task.getTaskID(), modification, formattedDate);

        }
        if (task.getUserID() != 0) {
            TaskUsers taskUsers = new TaskUsers();
            taskUsers.setTaskID(task.getTaskID());
            taskUsers.setUserID(task.getUserID());
            taskUsersRepo.save(taskUsers);
            String modification = "User added: " + task.getUserID();
            UserDetails userDetails = userRepo.findByUserID(task.getUserID());
            listOfUsers.add(userDetails);
            taskRepo.findByTaskID(task.getTaskID()).setUsers(listOfUsers);
            saveToTaskHistory(task.getTaskID(), modification, formattedDate);
        }
        if (task.getStringStatus() != null) {
            if(taskFromRepo.getUsers() != null){
                if(task.getStringStatus().equals("move forward")){
                    taskFromRepo.setStatus(taskFromRepo.getStatus().transition());
                }
            }
            String modification = "Status Changed: " + taskFromRepo.getStatus();
            saveToTaskHistory(task.getTaskID(), modification, formattedDate);
        }
        if (task.getTaskDescription() != null) {
            taskFromRepo.setDescription(task.getTaskDescription());
            String modification = "Modified description: " + task.getTaskDescription();
            saveToTaskHistory(task.getTaskID(), modification, formattedDate);

        }
        if (task.getTaskName() != null) {
            taskFromRepo.setTaskName(task.getTaskName());
            String modification = "New taskName: " + task.getTaskName();
            saveToTaskHistory(task.getTaskID(), modification, formattedDate);
        }
        taskFromRepo.setTimeUpdated(formattedDate);
        return taskRepo.save(taskFromRepo);
    }

    private void saveToTaskHistory(long taskID, String modification, String value) {
        TaskHistoryTable taskHistoryTable = new TaskHistoryTable();
        taskHistoryTable.setTaskID(taskID);
        taskHistoryTable.setModification(modification);
        taskHistoryTable.setTime(value);
        taskHistoryTableRepository.save(taskHistoryTable);
    }

    @Override
    public List<TaskComment> getComments(long taskID) {
        return taskCommentRepo.findByTaskID(taskID);
    }

    @Override
    public List<TaskUsers> getTaskUsers(long taskID) {
        return taskUsersRepo.findByTaskID(taskID);
    }

    @Override
    public List<TaskHistoryTable> getHistoryTable(long taskID) {
        return taskHistoryTableRepository.findByTaskID(taskID);
    }


}
