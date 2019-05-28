package com.univerage.univerage.repository;

import com.univerage.univerage.model.Course;
import com.univerage.univerage.model.CourseAverage;
import com.univerage.univerage.model.Instructor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "course", path = "course")
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    List<Course> findCourseBySubjectAndNumber(@Param("subject") String subject, @Param("number") int number);
}
