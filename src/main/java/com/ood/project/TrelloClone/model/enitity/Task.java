package com.ood.project.TrelloClone.model.enitity;

import com.ood.project.TrelloClone.model.task.Status;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long taskID;
    @NonNull
    private String taskName;
    private String timeCreated;
    private String ETC;
    private Status status;
    private String description;
    private String timeUpdated;
}
