package com.ood.project.TrelloClone.service;

import com.ood.project.TrelloClone.model.UserDetails;

import java.util.List;

public interface UserService {

    UserDetails saveUser(UserDetails userDetails);
    UserDetails getUser(long userID);
    List<UserDetails> getAllUsers();
    void deleteUser(long userID);

}
