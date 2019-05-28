package com.univerage.univerage.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CourseAverage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Course course;
    @OneToOne
    private Term term;
    @OneToMany
    private Set<Instructor> instructor;

    private int size;
    private double average;
}