package com.ood.project.TrelloClone.model.task;

import com.ood.project.TrelloClone.model.enitity.Task;
import com.ood.project.TrelloClone.model.enitity.UserDetails;
import lombok.Data;

import java.util.List;

@Data
public class TaskResponse {

    private Task task;
    private List<String> comments;
    private List<UserDetails> userDetails;
}
