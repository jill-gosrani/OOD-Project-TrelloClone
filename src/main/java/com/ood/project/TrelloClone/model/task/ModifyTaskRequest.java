package com.ood.project.TrelloClone.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyTaskRequest {
    @NonNull
    long taskID;
    String stringStatus;
    String taskName;
    String taskDescription;
    String comment;
    long userID;

}
