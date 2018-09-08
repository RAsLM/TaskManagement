package com.rasl.pojo;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;


/**
 * Created by ruslan on 19.02.2018.
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @OneToOne
    private Task task;

    //@Column
    //private Instant startTime;

    //@Column
    //private Instant endTime;

    @Column
    private Long spendTime;
}