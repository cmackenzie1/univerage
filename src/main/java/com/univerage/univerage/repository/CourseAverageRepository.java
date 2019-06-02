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

import com.univerage.univerage.model.mongo.CourseAverage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "courseavg", path = "courseavg")
public interface CourseAverageRepository extends MongoRepository<CourseAverage, String> {
    List<CourseAverage> findAllByCourseSubject(@Param("subject") String subject);

    List<CourseAverage> findAllByCourse_SubjectAndTerm_SemesterAndTerm_Year(@Param("subject") String subject,
                                                                            @Param("semester") String semester,
                                                                            @Param("year") int year);
}
