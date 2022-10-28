package com.ood.project.TrelloClone.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    @OneToMany
    @JoinColumn(name = "taskID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserDetails> users = new ArrayList<>();
    private String timeUpdated;
    @OneToMany
    @JoinColumn(name = "taskID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<TaskComment> taskComments = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "taskID")
    private List<TaskHistoryTable> taskHistoryTables = new ArrayList<>();


}
