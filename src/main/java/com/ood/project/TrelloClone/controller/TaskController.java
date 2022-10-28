package com.ood.project.TrelloClone.controller;

import com.ood.project.TrelloClone.model.*;
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
    public ResponseEntity<List<Task>> getTasks(){
        return ResponseEntity.ok().body(taskService.getAllTask());
    }
    @PostMapping("/add")
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        return ResponseEntity.ok().body(taskService.saveTask(task));
    }
    @PutMapping("/modifyTask")
    public ResponseEntity<Task> modifyTask(@RequestBody ModifyTask task) {
        return ResponseEntity.ok().body(taskService.modifyTask(task));
    }
    @PutMapping("/addComment")
    public ResponseEntity<Task> addComment(@RequestBody TaskComment taskComment) {
        return ResponseEntity.ok().body(taskService.addComment(taskComment));
    }
    @PostMapping("/addUsers")
    public ResponseEntity<Task> addUsers(@RequestBody TaskUsers taskUsers) {
        return ResponseEntity.ok().body(taskService.addUsers(taskUsers));
    }

    @GetMapping("/get")
    public ResponseEntity<Task> getTaskById(@RequestParam long taskID){
        return ResponseEntity.ok().body(taskService.getTask(taskID));
    }
    @GetMapping("/getComments")
    public ResponseEntity<List<TaskComment>> getTaskCommentsById(@RequestParam long taskID){
        return ResponseEntity.ok().body(taskService.getComments(taskID));
    }
    @GetMapping("/getUsers")
    public ResponseEntity<List<TaskUsers>> getTaskUsersById(@RequestParam long taskID){
        return ResponseEntity.ok().body(taskService.getTaskUsers(taskID));
    }
    @GetMapping("/getHistory")
    public ResponseEntity<List<TaskHistoryTable>> getTaskHistoryById(@RequestParam long taskID){
        return ResponseEntity.ok().body(taskService.getHistoryTable(taskID));
    }

}
