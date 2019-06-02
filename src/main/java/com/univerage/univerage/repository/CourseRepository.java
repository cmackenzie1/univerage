package com.univerage.univerage.repository;

import com.univerage.univerage.model.mongo.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "course", path = "course")
public interface CourseRepository extends MongoRepository<Course, String> {
    Course findCourseBySubjectAndNumber(@Param("subject") String subject, @Param("number") int number);
}
