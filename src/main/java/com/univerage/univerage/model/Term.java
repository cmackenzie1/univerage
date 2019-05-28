package com.univerage.univerage.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String semester;
    private int year;
}
