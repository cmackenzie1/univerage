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

package com.univerage.uofa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@CompoundIndexes(value = {
        @CompoundIndex(unique = true, def = "{'term': 1, 'course': 1}")
})
public class Course {
    @Id
    private String id;

    @Field("term")
    private Integer term;

    @Field("course")
    private Integer course;

    @Field("subject")
    private String subject;

    @Field("subjecttitle")
    @JsonProperty("subjecttitle")
    private String subjectTitle;

    @Field("catalog")
    private String catalog;

    @Field("coursetitle")
    @JsonProperty("coursetitle")
    private String courseTitle;

    @Field("coursedescription")
    @JsonProperty("coursedescription")
    private String courseDescription;

    @Field("facultycode")
    @JsonProperty("facultycode")
    private String facultyCode;

    @Field("faculty")
    private String faculty;

    @Field("departmentcode")
    @JsonProperty("departmentcode")
    private String departmentCode;

    @Field("department")
    private String department;

    @Field("career")
    private String career;

    @Field("units")
    private Double units;

    @Field("asstring")
    @JsonProperty("asstring")
    private String asString;
}
