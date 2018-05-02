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
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @JoinColumn
    @ManyToOne
    private User user;
}