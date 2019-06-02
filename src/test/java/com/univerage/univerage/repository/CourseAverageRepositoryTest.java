package com.univerage.univerage.repository;

import com.google.common.collect.Sets;
import com.univerage.univerage.model.mongo.Course;
import com.univerage.univerage.model.mongo.CourseAverage;
import com.univerage.univerage.model.mongo.Instructor;
import com.univerage.univerage.model.mongo.Term;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class CourseAverageRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CourseAverageRepository courseAverageRepository;

    @Test
    void whenFindAllByCourse_Subject_returnListOfCourseAveragesForSubject() {
        Course course1 = Course.builder()
                .subject("CART")
                .number(101)
                .description("Beep boop")
                .build();
        mongoTemplate.save(course1);
        Instructor instructorTony = Instructor.builder()
                .firstName("Tony")
                .lastName("Stark")
                .build();
        mongoTemplate.save(instructorTony);
        Term fall2019 = Term.builder()
                .semester("Fall")
                .year(2019)
                .build();
        mongoTemplate.save(fall2019);
        CourseAverage courseAverage1 = CourseAverage.builder()
                .course(course1)
                .term(fall2019)
                .instructor(Sets.newHashSet(instructorTony))
                .average(1.1)
                .section("A1")
                .size(10)
                .build();
        mongoTemplate.save(courseAverage1);
        List<CourseAverage> found = courseAverageRepository.findAllByCourseSubject("CART");
        for (CourseAverage courseAverage : found) {
            assertThat(courseAverage).isInstanceOf(CourseAverage.class);
            assertThat(courseAverage.getCourse().getSubject()).isEqualTo("CART");
        }
    }

    @Test
    void whenFindAllByCourse_SubjectAndTerm_SemesterAndTerm_Year_thenReturnMatchingListOfCourseAverages() {
        // given
        Course course1 = Course.builder()
                .subject("CART")
                .number(102)
                .build();
        mongoTemplate.save(course1);
        Course course2 = Course.builder()
                .subject("CART")
                .number(103)
                .build();
        mongoTemplate.save(course2);
        Course course3 = Course.builder()
                .subject("CART")
                .number(104)
                .build();
        mongoTemplate.save(course3);
        Term term1 = Term.builder()
                .semester("CART_FALL")
                .year(2019)
                .build();
        mongoTemplate.save(term1);
        CourseAverage courseAverage1 = CourseAverage.builder()
                .course(course1)
                .term(term1)
                .build();
        mongoTemplate.save(courseAverage1);
        CourseAverage courseAverage2 = CourseAverage.builder()
                .course(course2)
                .term(term1)
                .build();
        mongoTemplate.save(courseAverage2);
        CourseAverage courseAverage3 = CourseAverage.builder()
                .course(course3)
                .term(term1)
                .build();
        mongoTemplate.save(courseAverage3);
        // when
        List<CourseAverage> courseAverages = courseAverageRepository.findAllByCourse_SubjectAndTerm_SemesterAndTerm_Year(
                "CART",
                "CART_FALL",
                2019);
        // then
        for (CourseAverage courseAverage : courseAverages) {
            assertThat(courseAverage).isInstanceOf(CourseAverage.class);
            assertThat(courseAverage.getCourse().getSubject()).isEqualTo("CART");
        }
    }

}