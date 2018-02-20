package com.rasl.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ruslan on 19.02.2018.
 */
@Entity
public class Tasks {
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
    private Integer tags;

    @JoinColumn
    @ManyToOne
    private Integer parentTask;

    @Column
    private Date totalTime;

    @Column
    private Date planTime;

    @JoinColumn
    @OneToOne
    private Integer status;

    public Tasks() {
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

    public Integer getTags() {
        return tags;
    }

    public void setTags(Integer tags) {
        this.tags = tags;
    }

    public Integer getParentTask() {
        return parentTask;
    }

    public void setParentTask(Integer parentTask) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
