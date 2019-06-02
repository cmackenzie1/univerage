package com.univerage.univerage.repository;

import com.univerage.univerage.model.mongo.CourseAverage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "courseavg", path = "courseavg")
public interface CourseAverageRepository extends MongoRepository<CourseAverage, String> {
    List<CourseAverage> findAllByCourseSubject(@Param("subject") String subject);

    List<CourseAverage> findAllByCourse_SubjectAndTerm_SemesterAndTerm_Year(@Param("subject") String subject,
                                                                            @Param("semester") String semester,
                                                                            @Param("year") int year);
}
