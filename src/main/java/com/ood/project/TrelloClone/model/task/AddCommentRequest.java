package com.ood.project.TrelloClone.model.task;

import lombok.Data;

@Data
public class AddCommentRequest {
    private long taskID;
    private String comment;
}
