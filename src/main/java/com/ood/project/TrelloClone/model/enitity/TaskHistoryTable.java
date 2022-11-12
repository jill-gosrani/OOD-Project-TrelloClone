package com.ood.project.TrelloClone.model.enitity;

import com.ood.project.TrelloClone.model.task.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskHistoryTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskHistoryID;

    private long taskID;
    @NonNull
    private String taskName;
    private String timeCreated;
    private String ETC;
    private Status status;
    private String description;
    private String timeUpdated;
    @NonNull
    private String modification;
    @NonNull
    private String time;
    private String tag;
    private boolean isUndone;
}
