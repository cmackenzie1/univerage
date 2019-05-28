package com.univerage.univerage.repository;

import com.univerage.univerage.model.Instructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InstructorRepositoryTest {

    private static final String QUERY_FIRSTNAME_CASE_INSENSITIVE = "bRuCe";
    private static final String INSTRUCTOR1_FIRSTNAME = "Bruce";
    private static final String INSTRUCTOR1_LASTNAME = "Banner";
    private static final String INSTRUCTOR2_LASTNAME = "Hulk";
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    public void whenFindByFirstName_thenReturnListOfMatchingInstructors() {
        // given
        Instructor alfred = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR1_LASTNAME).build();
        Instructor alfred2 = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR2_LASTNAME).build();
        entityManager.persistAndFlush(alfred);
        entityManager.persistAndFlush(alfred2);

        // when
        List<Instructor> results = instructorRepository.findInstructorsByFirstNameIgnoreCase(INSTRUCTOR1_FIRSTNAME);

        // then
        assertEquals(results.size(), 2);
        for (Instructor instructor : results) {
            assertThat(instructor.getFirstName()).isEqualTo(alfred.getFirstName());
        }
    }

    @Test
    public void whenFindByLastName_thenReturnListOfMatchingInstructors() {
        // given
        Instructor alfred = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR1_LASTNAME).build();
        Instructor alfred2 = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR2_LASTNAME).build();
        entityManager.persistAndFlush(alfred);
        entityManager.persistAndFlush(alfred2);

        // when
        List<Instructor> results = instructorRepository.findInstructorsByLastNameIgnoreCase(INSTRUCTOR2_LASTNAME);

        // then
        assertEquals(results.size(), 1);
        for (Instructor instructor : results) {
            assertThat(instructor.getLastName()).isEqualTo(alfred2.getLastName());
        }
    }

    @Test
    public void whenFindByFirstNameAndLastName_thenReturnListOfMatchingInstructors() {
        // given
        Instructor alfred = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR1_LASTNAME).build();
        Instructor alfred2 = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR2_LASTNAME).build();
        entityManager.persistAndFlush(alfred);
        entityManager.persistAndFlush(alfred2);

        // when
        List<Instructor> results = instructorRepository.findInstructorByFirstNameAndLastNameIgnoreCase(INSTRUCTOR1_FIRSTNAME, INSTRUCTOR2_LASTNAME);

        // then
        assertEquals(results.size(), 1);
        for (Instructor instructor : results) {
            assertThat(instructor).isEqualTo(alfred2);
        }
    }

    @Test
    public void whenFindByFirstName_withCaseInsensitiveQuery_thenReturnListOfInstructors() {
        // given
        Instructor alfred = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR1_LASTNAME).build();
        Instructor alfred2 = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR2_LASTNAME).build();
        entityManager.persistAndFlush(alfred);
        entityManager.persistAndFlush(alfred2);

        // when
        List<Instructor> results = instructorRepository.findInstructorsByFirstNameIgnoreCase(QUERY_FIRSTNAME_CASE_INSENSITIVE);

        // then
        assertEquals(results.size(), 2);
        for (Instructor instructor : results) {
            assertThat(instructor.getFirstName()).isEqualToIgnoringCase(INSTRUCTOR1_FIRSTNAME);
        }
    }

    @Test
    public void whenFindByFirstNameDoesNotExist_thenReturnEmptyListOfInstructors() {
        // given
        Instructor alfred = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR1_LASTNAME).build();
        Instructor alfred2 = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR2_LASTNAME).build();
        entityManager.persistAndFlush(alfred);
        entityManager.persistAndFlush(alfred2);

        // when
        List<Instructor> results = instructorRepository.findInstructorsByFirstNameIgnoreCase("James");

        // then
        assertEquals(results.size(), 0);
    }

}