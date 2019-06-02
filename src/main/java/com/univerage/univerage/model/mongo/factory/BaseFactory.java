package com.univerage.univerage.model.mongo.factory;

import com.github.javafaker.Faker;

public class BaseFactory<T> {
    Faker faker = new Faker();

    public T create() {
        return null;
    }
}
