package com.rasl.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ruslan on 19.02.2018.
 */
@Entity
public class WorkLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String task_id;

    @Column
    private Date startTime;

    @Column
    private Date end_time;


    public WorkLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setName(String task_id) {
        this.task_id = task_id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
}
