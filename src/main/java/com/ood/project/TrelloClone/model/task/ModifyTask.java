package com.ood.project.TrelloClone.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyTask {

    long taskID;
    String stringStatus;
    String taskName;
    String taskDescription;
    String comment;
    long userID;

}
