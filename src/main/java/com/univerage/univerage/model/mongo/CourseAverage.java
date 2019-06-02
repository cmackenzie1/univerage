package com.univerage.univerage.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class CourseAverage {

    @Id
    @NotNull
    private String id;

    @NotNull
    private Course course;
    @NotNull
    private Term term;
    @NotNull
    private Set<Instructor> instructor;
    @NotNull
    private String section;
    @NotNull
    private int size;
    @NotNull
    private double average;
}