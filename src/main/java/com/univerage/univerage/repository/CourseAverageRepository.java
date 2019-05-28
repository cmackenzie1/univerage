package com.univerage.univerage.repository;

import com.univerage.univerage.model.CourseAverage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseAverageRepository extends PagingAndSortingRepository<CourseAverage, Long> {
    List<CourseAverage> findCourseAveragesByCourse_Subject(@Param("subject") String subject);

    List<CourseAverage> findCourseAveragesByCourse_SubjectAndTerm_SemesterAndTerm_Year(@Param("subject") String subject,
                                                                                       @Param("semester") String semester,
                                                                                       @Param("year") int year);
}
