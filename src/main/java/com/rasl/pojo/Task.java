package com.rasl.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ruslan on 19.02.2018.
 */
@Entity
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
    @OneToOne
    private Tag tag;

    @JoinColumn
    @OneToOne
    private Task parentTask;

    @Column
    private Date totalTime;

    @Column
    private Date planTime;

    @JoinColumn
    @OneToOne
    private Status status;

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public Date getTime() {
        return totalTime;
    }

    public void setTime(Date totalTime) {
        this.totalTime = totalTime;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
