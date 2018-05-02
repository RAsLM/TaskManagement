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
@Getter
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
    private int spentTime;

    private String readableTime;

    @Column
    private boolean inProcess;

    public Integer getId() {
        return id;
    }

    public String getReadableTime() {

        int sec = spentTime;
        int min = spentTime / 60;
        int hours = min > 0 ? min / 60 : 0;



        if (hours > 0){

            return String.format("%d:%d:%d", hours, min, sec);
        }

        if (min > 0){
            sec = spentTime % 60;
            return String.format("%d:%d:%d", hours, min, sec);
        }

            return String.format("%d:%d:%d", hours, min, sec);
    }
}