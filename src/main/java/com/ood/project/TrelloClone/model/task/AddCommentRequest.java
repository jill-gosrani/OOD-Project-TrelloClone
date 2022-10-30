package com.ood.project.TrelloClone.model.task;

import lombok.Data;
import lombok.NonNull;

@Data
public class AddCommentRequest {
    @NonNull
    private long taskID;
    @NonNull
    private String comment;
    @NonNull
    private long userID;
}
