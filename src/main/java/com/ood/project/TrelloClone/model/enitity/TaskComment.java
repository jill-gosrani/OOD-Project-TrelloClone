package com.ood.project.TrelloClone.model.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentID;
    @NonNull
    private String comment;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "userID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserDetails userDetails;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "taskID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;

}
