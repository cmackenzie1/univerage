package com.univerage.univerage.repository;

import com.univerage.univerage.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "course", path = "course")
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    Course findCourseBySubjectAndNumber(@Param("subject") String subject, @Param("number") int number);
}
