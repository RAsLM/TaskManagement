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

    public Task(String name, Tag tag, Status status) {
        this.name = name;
        this.tag = tag;
        this.status = status;
    }

    public Task(String name, Tag tag, Task parentTask, Status status) {
        this.name = name;
        this.tag = tag;
        this.parentTask = parentTask;
        this.status = status;
    }

    public Task(String name, String description, Tag tag, Task parentTask, Date totalTime, Date planTime, Status status) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.parentTask = parentTask;
        this.totalTime = totalTime;
        this.planTime = planTime;
        this.status = status;
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

    public void setParentTask(Task parent_task) {
        this.parentTask = parent_task;
    }

    public Date getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Date totalTime) {
        this.totalTime = totalTime;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date plan_time) {
        this.planTime = plan_time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
