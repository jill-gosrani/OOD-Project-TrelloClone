package com.ood.project.TrelloClone.service;

import com.ood.project.TrelloClone.model.enitity.*;
import com.ood.project.TrelloClone.model.task.*;
import com.ood.project.TrelloClone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {
    private final TaskRepository taskRepo;
    private final TaskCommentRepository taskCommentRepo;
    private final TaskUsersRepository taskUsersRepo;
    private final TaskHistoryTableRepository taskHistoryTableRepository;
    private final UserRepository userRepo;
    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String formattedDate = myDateObj.format(myFormatObj);

    /**
     * Takes task body
     * Sets status to default value TODO
     * sets Estimated Time To complete to 2 weeks
     * Returns Task
     * @param task
     * @return
     */
    @Override
    public TaskResponse saveTask(Task task) {
        task.setTimeCreated(formattedDate);
        task.setStatus(Status.TODO);
        task.setETC("2 weeks");
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTask(taskRepo.save(task));
        taskResponse.setUserDetails(taskUsersRepo.findByTask(task).stream().map(TaskUsers::getUserDetails).collect(Collectors.toList()));
        taskResponse.setComments(taskCommentRepo.findByTask(task).stream().map(TaskComment::getComment).collect(Collectors.toList()));
        return taskResponse;
    }

    /**
     * Takes taskID
     * Returns that task
     * @param taskID
     * @return
     */
    @Override
    public TaskResponse getTask(long taskID) {
        Task task = taskRepo.findByTaskID(taskID);
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTask(task);
        taskResponse.setComments(taskCommentRepo.findByTask(task).stream().map(TaskComment::getComment).collect(Collectors.toList()));
        taskResponse.setUserDetails(taskUsersRepo.findByTask(task).stream().map(TaskUsers::getUserDetails).collect(Collectors.toList()));
        return taskResponse;
    }

    /**
     * Fetches all takes in order of Status
     * @return
     */
    @Override
    public List<TaskResponse> getAllTask() {

        List<TaskResponse> taskResponseList = new ArrayList<>();
        List<Task> tasks = taskRepo.findByOrderByStatusAsc();
        for (Task task : tasks) {
            TaskResponse taskResponse = new TaskResponse();
            taskResponse.setTask(task);
            taskResponse.setComments(taskCommentRepo.findByTask(task).stream().map(TaskComment::getComment).collect(Collectors.toList()));
            taskResponse.setUserDetails(taskUsersRepo.findByTask(task).stream().map(TaskUsers::getUserDetails).collect(Collectors.toList()));
            taskResponseList.add(taskResponse);
        }
        return taskResponseList;
    }

    /**
     * Takes taskID, userID, and comment
     * saves the comment in the task
     * @param addCommentRequest
     * @return
     */
    @Override
    public TaskResponse addComment(AddCommentRequest addCommentRequest) {
        TaskComment taskComment = new TaskComment();
        TaskResponse taskResponse = new TaskResponse();
        taskComment.setComment(addCommentRequest.getComment());
        taskComment.setTask(taskRepo.findByTaskID(addCommentRequest.getTaskID()));
        taskComment.setUserDetails(userRepo.findByUserID(addCommentRequest.getUserID()));
        taskCommentRepo.save(taskComment);
        Task task = taskRepo.findByTaskID(addCommentRequest.getTaskID());
        task.setTimeUpdated(formattedDate);
        taskResponse.setTask(taskRepo.save(task));
        taskResponse.setComments(taskCommentRepo.findByTask(task).stream().map(TaskComment::getComment).collect(Collectors.toList()));
        taskResponse.setUserDetails(taskUsersRepo.findByTask(task).stream().map(TaskUsers::getUserDetails).collect(Collectors.toList()));

        return taskResponse;
    }

    /**
     * Takes usersID, taskID
     * Saves that user to task
     * @param addUserRequest
     * @return
     */
    @Override
    public TaskResponse addUsers(AddUserRequest addUserRequest) {
        TaskResponse taskResponse = new TaskResponse();
        Task task = taskRepo.findByTaskID(addUserRequest.getTaskID());
        UserDetails userDetails = userRepo.findByUserID(addUserRequest.getUserID());
        TaskUsers taskUsers = new TaskUsers();
        taskUsers.setTask(task);
        taskUsers.setUserDetails(userDetails);
        taskUsersRepo.save(taskUsers);
        task.setTimeUpdated(formattedDate);
        taskResponse.setTask(taskRepo.save(task));
        taskResponse.setComments(taskCommentRepo.findByTask(task).stream().map(TaskComment::getComment).collect(Collectors.toList()));
        taskResponse.setUserDetails(taskUsersRepo.findByTask(task).stream().map(TaskUsers::getUserDetails).collect(Collectors.toList()));
        return taskResponse;
    }


    /**
     * Takes ModifyTaskRequest body
     * ModifyTaskRequest Contains:
     *     long taskID;
     *     String stringStatus;
     *     String taskName;
     *     String taskDescription;
     *     String comment;
     *     long userID;
     * returns task with modifications
     * @param modifyTaskRequest
     * @return
     */
    @Override
    public TaskResponse modifyTask(ModifyTaskRequest modifyTaskRequest) {

        Task taskFromRepo = taskRepo.findByTaskID(modifyTaskRequest.getTaskID());
        TaskResponse taskResponse = new TaskResponse();
        if (modifyTaskRequest.getComment() != null) {
            TaskComment taskComment = new TaskComment();
            taskComment.setTask(taskFromRepo);
            taskComment.setComment(modifyTaskRequest.getComment());
            taskComment.setUserDetails(userRepo.findByUserID(modifyTaskRequest.getUserID()));
            taskCommentRepo.save(taskComment);
            String modification = "Comment added: " + modifyTaskRequest.getComment();
            saveToTaskHistory(taskFromRepo, modification, formattedDate);
        }
        if (modifyTaskRequest.getUserID() != 0) {
            TaskUsers taskUsers = new TaskUsers();
            taskUsers.setTask(taskFromRepo);
            taskUsers.setUserDetails(userRepo.findByUserID(modifyTaskRequest.getUserID()));
            taskUsersRepo.save(taskUsers);
            String modification = "User added: " + modifyTaskRequest.getUserID();
            saveToTaskHistory(taskFromRepo, modification, formattedDate);
        }
        if (modifyTaskRequest.getStringStatus() != null) {
            if (taskUsersRepo.findByTask(taskFromRepo) != null) {
                if (modifyTaskRequest.getStringStatus().equals("move forward")) {
                    taskFromRepo.setStatus(taskFromRepo.getStatus().transition());
                    String modification = "Status Changed: " + taskFromRepo.getStatus();
                    saveToTaskHistory(taskFromRepo, modification, formattedDate);
                }
            }
        }
        if (modifyTaskRequest.getTaskDescription() != null) {
            taskFromRepo.setDescription(modifyTaskRequest.getTaskDescription());
            String modification = "Modified description: " + modifyTaskRequest.getTaskDescription();
            saveToTaskHistory(taskFromRepo, modification, formattedDate);

        }
        if (modifyTaskRequest.getTaskName() != null) {
            taskFromRepo.setTaskName(modifyTaskRequest.getTaskName());
            String modification = "New taskName: " + modifyTaskRequest.getTaskName();
            saveToTaskHistory(taskFromRepo, modification, formattedDate);
        }
        taskFromRepo.setTimeUpdated(formattedDate);
        taskResponse.setTask(taskFromRepo);
        taskResponse.setComments(taskCommentRepo.findByTask(taskFromRepo).stream().map(TaskComment::getComment).collect(Collectors.toList()));
        taskResponse.setUserDetails(taskUsersRepo.findByTask(taskFromRepo).stream().map(TaskUsers::getUserDetails).collect(Collectors.toList()));
        return taskResponse;
    }

    /**
     * Takes Task, Modifications, Value(TimeStamp)
     * Saves History of the Task
     * @param task
     * @param modification
     * @param value
     */
    private void saveToTaskHistory(Task task, String modification, String value) {
        TaskHistoryTable taskHistoryTable = new TaskHistoryTable();
        taskHistoryTable.setTaskID(task.getTaskID());
        taskHistoryTable.setTaskName(task.getTaskName());
        taskHistoryTable.setETC(task.getETC());
        taskHistoryTable.setStatus(task.getStatus());
        taskHistoryTable.setDescription(task.getDescription());
        taskHistoryTable.setTimeCreated(task.getTimeCreated());
        taskHistoryTable.setTimeUpdated(task.getTimeUpdated());
        taskHistoryTable.setModification(modification);
        taskHistoryTable.setTime(value);
        taskHistoryTableRepository.save(taskHistoryTable);
    }

    /**
     * Takes taskID
     * Deletes that task
     * @param taskID
     */
    @Override
    public void deleteTaskByID(long taskID) {
        taskRepo.deleteById(taskID);
    }

    /**
     * Takes taskID
     * Adds comment to the task
     * @param taskID
     * @return
     */
    @Override
    public List<TaskComment> getComments(long taskID) {
        Task task = taskRepo.findByTaskID(taskID);
        return taskCommentRepo.findByTask(task);
    }

    /**
     * Takes taskID
     * Returns the list of users on that task
     * @param taskID
     * @return
     */
    @Override
    public List<TaskUsers> getTaskUsers(long taskID) {
        Task task = taskRepo.findByTaskID(taskID);
        return taskUsersRepo.findByTask(task);
    }

    /**
     * Takes taskID
     * returns History of that task
     * @param taskID
     * @return
     */
    @Override
    public List<TaskHistoryTable> getHistoryTable(long taskID) {
        return taskHistoryTableRepository.findByTaskID(taskID);
    }
}
