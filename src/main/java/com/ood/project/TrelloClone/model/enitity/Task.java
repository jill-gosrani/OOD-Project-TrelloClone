package com.ood.project.TrelloClone.model.enitity;

import com.ood.project.TrelloClone.model.task.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long taskID;
    @NonNull
    private String taskName;
    private String timeCreated;
    private String ETC;
    private Status status;
    private String description;
    private String timeUpdated;
}
