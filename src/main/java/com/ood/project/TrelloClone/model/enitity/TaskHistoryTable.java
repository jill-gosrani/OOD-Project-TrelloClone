package com.ood.project.TrelloClone.model.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "taskID")
    private Task task;
    @NonNull
    private String modification;
    @NonNull
    private String time;
}
