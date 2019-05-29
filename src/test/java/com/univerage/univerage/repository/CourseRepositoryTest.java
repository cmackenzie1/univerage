package com.univerage.univerage.repository;

import com.univerage.univerage.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void whenFindCourseBySubjectAndNumber_thenReturnCourse() {
        // given
        Course chem101 = Course.builder().subject("CHEM").number(101).build();
        entityManager.persistAndFlush(chem101);

        // when
        Course found = courseRepository.findCourseBySubjectAndNumber("CHEM", 101);
        Course notFound = courseRepository.findCourseBySubjectAndNumber("BLAH", 101);

        // then
        assertThat(found).isNotNull();
        assertThat(notFound).isNull();
        assertThat(found.getSubject()).isEqualTo(chem101.getSubject());
        assertThat(found.getId()).isEqualTo(chem101.getId());
    }

    @Test(expected = PersistenceException.class)
    public void whenCourseCreatedAlreadyExists_thenExpectPersistenceException() {
        // given
        Course chem101 = Course.builder().subject("CHEM").number(101).build();
        Course chem101_2 = Course.builder().subject("CHEM").number(101).build();

        // when
        entityManager.persistAndFlush(chem101);

        // then
        entityManager.persistAndFlush(chem101_2); // Throws PersistenceException
    }

}