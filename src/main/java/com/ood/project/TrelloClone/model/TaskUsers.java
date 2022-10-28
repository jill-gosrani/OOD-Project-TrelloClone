package com.ood.project.TrelloClone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userTaskID;
    @NonNull
    private long taskID;
    @NonNull
    private long userID;
}
