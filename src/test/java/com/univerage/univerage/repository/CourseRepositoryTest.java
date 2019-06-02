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

import com.univerage.univerage.model.mongo.Course;
import lombok.extern.apachecommons.CommonsLog;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@CommonsLog
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void whenFindCourseBySubjectAndNumber_thenReturnCourse() {
        // given
        Course course = Course.builder().subject("CHEM").number(101).build();
        courseRepository.save(course);

        // when
        Course found = courseRepository.findCourseBySubjectAndNumber("CHEM", 101);
        Course notFound = courseRepository.findCourseBySubjectAndNumber("BLAH", 101);

        // then
        assertThat(found).isNotNull();
        assertThat(notFound).isNull();
        assertThat(found.getSubject()).isEqualTo(course.getSubject());
        assertThat(found.getId()).isEqualTo(course.getId());
    }

    @Test
    void whenCourseCreatedAlreadyExists_thenExpectPersistenceException() {
        // given
        Course course1 = Course.builder().subject("ACCT").number(101).build();
        Course course2 = Course.builder().subject("ACCT").number(101).build();

        // when
        courseRepository.insert(course1);

        // then
        Assertions.assertThatExceptionOfType(DuplicateKeyException.class).isThrownBy(() -> courseRepository.insert(course2));
    }

}