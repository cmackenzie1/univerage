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

import com.univerage.univerage.uofa.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void whenFindCourseByCourse_returnCourse() {
        Course course = Course.builder()
                .course("107763")
                .build();

        courseRepository.deleteAll();
        courseRepository.save(course);

        Course result = courseRepository.findCourseByCourse(course.getCourse());
        assertThat(result).isEqualToIgnoringGivenFields(course, "id");
    }

    @Test
    void whenFindCoursesByTerm_returnCourses() {
        Course course = Course.builder()
                .course("1")
                .term("1700")
                .build();

        Course course2 = Course.builder()
                .course("2")
                .term("1700")
                .build();

        courseRepository.deleteAll();
        courseRepository.save(course);
        courseRepository.save(course2);

        List<Course> result = courseRepository.findCoursesByTerm(course.getTerm());
        assertThat(result).hasSize(2);
        for (Course c :
                result) {
            assertThat(c.getTerm()).isEqualTo(course.getTerm());
        }
    }

    @Test
    void whenFindCoursesByIgnoreCaseSubject_returnCourses() {
        Course course = Course.builder()
                .course("1")
                .term("1200")
                .subject("CHEM")
                .build();

        Course course2 = Course.builder()
                .course("2")
                .term("1200")
                .subject("CMPUT")
                .build();

        courseRepository.deleteAll();
        courseRepository.save(course);
        courseRepository.save(course2);

        List<Course> result = courseRepository.findCoursesByIgnoreCaseSubject(course.getSubject());
        assertThat(result).hasSize(1);
    }

    @Test
    void whenFindCoursesByIgnoreCaseSubjectAndCatalog_returnCourses() {
        Course course = Course.builder()
                .course("1")
                .term("1200")
                .subject("CHEM")
                .build();

        Course course2 = Course.builder()
                .course("2")
                .term("1200")
                .subject("CMPUT")
                .catalog("101")
                .build();

        Course course3 = Course.builder()
                .course("3")
                .term("1200")
                .subject("CMPUT")
                .catalog("102")
                .build();

        courseRepository.deleteAll();
        courseRepository.save(course);
        courseRepository.save(course2);
        courseRepository.save(course3);

        List<Course> result = courseRepository.findCoursesByIgnoreCaseSubjectAndCatalog(course2.getSubject(), course2.getCatalog());
        assertThat(result).hasSize(1);
    }
}