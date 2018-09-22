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
    private Long spentTime;

    @Column
    private boolean inProcess;
}