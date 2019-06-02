package com.univerage.univerage.model.mongo.factory;

import com.univerage.univerage.model.mongo.Course;

public class CourseFactory extends BaseFactory<Course> {
    @Override
    public Course create() {
        return Course.builder()
                .subject(faker.country().currencyCode())
                .number(Integer.parseInt(faker.number().digits(3)))
                .description(faker.lorem().paragraph())
                .build();
    }
}

