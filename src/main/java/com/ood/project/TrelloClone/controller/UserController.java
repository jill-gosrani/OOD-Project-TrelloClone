package com.ood.project.TrelloClone.controller;

import com.ood.project.TrelloClone.model.enitity.UserDetails;
import com.ood.project.TrelloClone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<UserDetails> getUserById(@RequestParam long userID) {
        return ResponseEntity.ok().body(userService.getUser(userID));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDetails>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<UserDetails> addUser(@RequestBody UserDetails userDetails) {
        return ResponseEntity.ok().body(userService.saveUser(userDetails));
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam long userID) {
        userService.deleteUser(userID);
    }
}
