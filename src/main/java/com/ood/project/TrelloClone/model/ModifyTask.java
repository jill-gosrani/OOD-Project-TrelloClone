package com.ood.project.TrelloClone.model;

import com.ood.project.TrelloClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


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
