package com.univerage.univerage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class UniverageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniverageApplication.class, args);
    }

}
