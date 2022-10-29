package com.ood.project.TrelloClone.controller;

import com.ood.project.TrelloClone.model.enitity.Task;
import com.ood.project.TrelloClone.model.enitity.TaskComment;
import com.ood.project.TrelloClone.model.enitity.TaskHistoryTable;
import com.ood.project.TrelloClone.model.enitity.TaskUsers;
import com.ood.project.TrelloClone.model.task.AddCommentRequest;
import com.ood.project.TrelloClone.model.task.AddUserRequest;
import com.ood.project.TrelloClone.model.task.ModifyTaskRequest;
import com.ood.project.TrelloClone.model.task.TaskResponse;
import com.ood.project.TrelloClone.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TaskResponse>> getTasks() {
        return ResponseEntity.ok().body(taskService.getAllTask());
    }

    @PostMapping("/add")
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        return ResponseEntity.ok().body(taskService.saveTask(task));
    }

    @PutMapping("/modifyTask")
    public ResponseEntity<Task> modifyTask(@RequestBody ModifyTaskRequest task) {
        return ResponseEntity.ok().body(taskService.modifyTask(task));
    }

    @PutMapping("/addComment")
    public ResponseEntity<TaskResponse> addComment(@RequestBody AddCommentRequest addCommentRequest) {
        return ResponseEntity.ok().body(taskService.addComment(addCommentRequest));
    }

    @PostMapping("/addUsers")
    public ResponseEntity<TaskResponse> addUsers(@RequestBody AddUserRequest addUserRequest) {
        return ResponseEntity.ok().body(taskService.addUsers(addUserRequest));
    }

    @GetMapping("/get")
    public ResponseEntity<Task> getTaskById(@RequestParam long taskID) {
        return ResponseEntity.ok().body(taskService.getTask(taskID));
    }

    @GetMapping("/getComments")
    public ResponseEntity<List<TaskComment>> getTaskCommentsById(@RequestParam long taskID) {
        return ResponseEntity.ok().body(taskService.getComments(taskID));
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<TaskUsers>> getTaskUsersById(@RequestParam long taskID) {
        return ResponseEntity.ok().body(taskService.getTaskUsers(taskID));
    }

    @GetMapping("/getHistory")
    public ResponseEntity<List<TaskHistoryTable>> getTaskHistoryById(@RequestParam long taskID) {
        return ResponseEntity.ok().body(taskService.getHistoryTable(taskID));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestParam long taskID) {
        taskService.deleteTaskByID(taskID);
        return ResponseEntity.ok("Deleted task with taskId: " + taskID);
    }

}
