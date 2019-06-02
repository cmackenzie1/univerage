/*
 *    Copyright 2019 Cole Mackenzie
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.univerage.univerage.repository;

import com.univerage.univerage.model.mongo.Instructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class InstructorRepositoryTest {

    private static final String QUERY_FIRSTNAME_CASE_INSENSITIVE = "bRuCe";
    private static final String INSTRUCTOR1_FIRSTNAME = "Bruce";
    private static final String INSTRUCTOR1_LASTNAME = "Banner";
    private static final String INSTRUCTOR2_LASTNAME = "Hulk";

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void whenFindByFirstName_thenReturnListOfMatchingInstructors() {
        // given
        Instructor bruce = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR1_LASTNAME).build();
        Instructor hulk = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR2_LASTNAME).build();
        mongoTemplate.save(bruce);
        mongoTemplate.save(hulk);

        // when
        List<Instructor> results = instructorRepository.findInstructorsByFirstNameIgnoreCase(INSTRUCTOR1_FIRSTNAME);

        // then
        assertThat(results.size()).isEqualTo(2);
        for (Instructor instructor : results) {
            assertThat(instructor.getFirstName()).isEqualTo(bruce.getFirstName());
        }
    }

    @Test
    void whenFindByLastName_thenReturnListOfMatchingInstructors() {
        // given
        Instructor bruce = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR1_LASTNAME).build();
        Instructor hulk = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR2_LASTNAME).build();
        mongoTemplate.save(bruce);
        mongoTemplate.save(hulk);

        // when
        List<Instructor> results = instructorRepository.findInstructorsByLastNameIgnoreCase(INSTRUCTOR2_LASTNAME);

        // then
        for (Instructor instructor : results) {
            assertThat(instructor.getLastName()).isEqualTo(hulk.getLastName());
        }
    }

    @Test
    void whenFindByFirstNameAndLastName_thenReturnListOfMatchingInstructors() {
        // given
        Instructor bruce = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR1_LASTNAME).build();
        Instructor hulk = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR2_LASTNAME).build();
        mongoTemplate.save(bruce);
        mongoTemplate.save(hulk);

        // when
        List<Instructor> results = instructorRepository.findInstructorByFirstNameAndLastNameIgnoreCase(INSTRUCTOR1_FIRSTNAME, INSTRUCTOR2_LASTNAME);

        // then
        for (Instructor instructor : results) {
            assertThat(instructor).isEqualToComparingOnlyGivenFields(hulk, "firstName", "lastName");
        }
    }

    @Test
    void whenFindByFirstName_withCaseInsensitiveQuery_thenReturnListOfInstructors() {
        // given
        Instructor bruce = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR1_LASTNAME).build();
        Instructor hulk = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR2_LASTNAME).build();
        mongoTemplate.save(bruce);
        mongoTemplate.save(hulk);

        // when
        List<Instructor> results = instructorRepository.findInstructorsByFirstNameIgnoreCase(QUERY_FIRSTNAME_CASE_INSENSITIVE);

        // then
        for (Instructor instructor : results) {
            assertThat(instructor.getFirstName()).isEqualToIgnoringCase(INSTRUCTOR1_FIRSTNAME);
        }
    }

    @Test
    void whenFindByFirstNameDoesNotExist_thenReturnEmptyListOfInstructors() {
        // given
        Instructor bruce = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR1_LASTNAME).build();
        Instructor hulk = Instructor.builder().firstName(INSTRUCTOR1_FIRSTNAME).lastName(INSTRUCTOR2_LASTNAME).build();
        mongoTemplate.save(bruce);
        mongoTemplate.save(hulk);

        // when
        List<Instructor> results = instructorRepository.findInstructorsByFirstNameIgnoreCase("James");

        // then
        assertThat(results.size()).isEqualTo(0);
    }

}