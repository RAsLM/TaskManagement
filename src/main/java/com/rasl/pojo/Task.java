package com.rasl.pojo;

import lombok.*;

import javax.persistence.*;

/**
 * Created by ruslan on 19.02.2018.
 */

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @OneToOne
    private Tag tag;

    @JoinColumn
    @OneToOne
    private Task parentTask;

    @JoinColumn
    @OneToOne
    private Status status;

    @Column
    private Long spentTime;

    @Column
    private boolean inProcess;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public Tag getTag() {
        return tag;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public Status getStatus() {
        return status;
    }

    public Long getSpentTime() {
        if (spentTime == null){
            return 0L;
        }
        return spentTime;
    }

    public boolean isInProcess() {
        return inProcess;
    }
}