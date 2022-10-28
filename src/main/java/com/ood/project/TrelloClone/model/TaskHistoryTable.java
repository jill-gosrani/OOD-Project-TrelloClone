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
public class TaskHistoryTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskHistoryID;
    @NonNull
    private long taskID;
    @NonNull
    private String modification;
    @NonNull
    private String time;
}
