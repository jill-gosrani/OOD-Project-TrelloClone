package com.ood.project.TrelloClone.service;

import com.ood.project.TrelloClone.model.UserDetails;
import com.ood.project.TrelloClone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepository userRepo;

    @Override
    public UserDetails saveUser(UserDetails userDetails) {
        return userRepo.save(userDetails);
    }

    @Override
    public UserDetails getUser(long userID) {
        return userRepo.findByUserID(userID);
    }

    @Override
    public List<UserDetails> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(long userID) {
        userRepo.deleteById(userID);
    }
}
