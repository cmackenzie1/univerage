package com.univerage.univerage.model.mongo.factory;

import com.univerage.univerage.model.mongo.Instructor;

public class InstructorFactory extends BaseFactory<Instructor> {

    @Override
    public Instructor create() {
        return Instructor.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }
}
