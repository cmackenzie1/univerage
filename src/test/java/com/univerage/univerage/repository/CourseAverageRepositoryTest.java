package com.univerage.univerage.repository;

import com.google.common.collect.Sets;
import com.univerage.univerage.model.Course;
import com.univerage.univerage.model.CourseAverage;
import com.univerage.univerage.model.Instructor;
import com.univerage.univerage.model.Term;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseAverageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseAverageRepository courseAverageRepository;

    @Test
    public void whenFindAllByCourse_Subject_returnListOfCourseAveragesForSubject() {
        Course cmput101 = Course.builder()
                .subject("CMPUT")
                .number(101)
                .description("Beep boop")
                .build();
        entityManager.persistAndFlush(cmput101);
        Instructor instructorTony = Instructor.builder()
                .firstName("Tony")
                .lastName("Stark")
                .build();
        entityManager.persistAndFlush(instructorTony);
        Term fall2019 = Term.builder()
                .semester("Fall")
                .year(2019)
                .build();
        entityManager.persistAndFlush(fall2019);
        CourseAverage cmput101CourseAverage = CourseAverage.builder()
                .course(cmput101)
                .term(fall2019)
                .instructor(Sets.newHashSet(instructorTony))
                .average(1.1)
                .section("A1")
                .size(10)
                .build();
        entityManager.persistAndFlush(cmput101CourseAverage);
        List<CourseAverage> found = courseAverageRepository.findAllByCourseSubject("CMPUT");
        for (CourseAverage courseAverage : found) {
            assertThat(courseAverage).isInstanceOf(CourseAverage.class);
            assertThat(courseAverage.getCourse().getSubject()).isEqualTo("CMPUT");
        }
    }

    @Test
    public void whenFindAllByCourse_SubjectAndTerm_SemesterAndTerm_Year_thenReturnMatchingListOfCourseAverages() {
        // given
        Course cmput101 = Course.builder()
                .subject("CMPUT")
                .number(101)
                .build();
        entityManager.persistAndFlush(cmput101);
        Course cmput201 = Course.builder()
                .subject("CMPUT")
                .number(201)
                .build();
        entityManager.persistAndFlush(cmput201);
        Course chem101 = Course.builder()
                .subject("CHEM")
                .number(101)
                .build();
        entityManager.persistAndFlush(chem101);
        Term fall2019 = Term.builder()
                .semester("Fall")
                .year(2019)
                .build();
        entityManager.persistAndFlush(fall2019);
        CourseAverage cmput101CourseAverage = CourseAverage.builder()
                .course(cmput101)
                .term(fall2019)
                .build();
        entityManager.persistAndFlush(cmput101CourseAverage);
        CourseAverage cmput201CourseAverage = CourseAverage.builder()
                .course(cmput201)
                .term(fall2019)
                .build();
        entityManager.persistAndFlush(cmput201CourseAverage);
        CourseAverage chem101CourseAverage = CourseAverage.builder()
                .course(chem101)
                .term(fall2019)
                .build();
        entityManager.persistAndFlush(chem101CourseAverage);
        // when
        List<CourseAverage> courseAverages = courseAverageRepository.findAllByCourse_SubjectAndTerm_SemesterAndTerm_Year(
                "CMPUT",
                "Fall",
                2019);
        // then
        assertThat(courseAverages).hasSize(2);
        for (CourseAverage courseAverage : courseAverages) {
            assertThat(courseAverage).isInstanceOf(CourseAverage.class);
            assertThat(courseAverage.getCourse().getSubject()).isEqualTo("CMPUT");
        }
    }

}