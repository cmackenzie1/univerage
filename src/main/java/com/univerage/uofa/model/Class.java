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

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@CompoundIndexes(value = {
        @CompoundIndex(unique = true, def = "{'term': 1, 'course': 1, 'class': 1}")
})
public class Class {
    @Id
    private String id;

    @Field("term")
    private String term;

    @Field("course")
    private String course;

    @Field("class")
    @JsonProperty("class")
    private String _class;

    @Field("section")
    private String section;

    @Field("component")
    private String component;

    @Field("classtype")
    @JsonProperty("classtype")
    private String classType;

    @Field("classstatus")
    @JsonProperty("classstatus")
    private String classStatus;

    @Field("enrollstatus")
    @JsonProperty("enrollstatus")
    private String enrollStatus;

    @Field("capacity")
    private String capacity;

    @Field("startdate")
    @JsonProperty("startdate")
    private LocalDate startDate;

    @Field("enddate")
    @JsonProperty("enddate")
    private LocalDate endDate;

    @Field("session")
    private String session;

    @Field("campus")
    private String campus;

    @Field("location")
    private String location;

    @Field("autoenroll")
    @JsonProperty("autoenroll")
    private String autoEnroll;

    @Field("classtopic")
    @JsonProperty("classtopic")
    private String classTopic;

    @Field("classnotes")
    @JsonProperty("classnotes")
    private String classNotes;

    @Field("consent")
    private String consent;

    @Field("gradingbasis")
    @JsonProperty("gradingbasis")
    private String gradingBasis;

    @Field("instructionmode")
    @JsonProperty("instructionmode")
    private String instructionMode;

    @Field("units")
    private String units;

    @Field("classurl")
    @JsonProperty("classurl")
    private String classUrl;

    @Field("instructoruid")
    @JsonProperty("instructoruid")
    private String instructorUid;

    @Field("examstatus")
    @JsonProperty("examstatus")
    private String examStatus;

    @Field("examdate")
    @JsonProperty("examdate")
    private LocalDate examDate;

    @Field("examstarttime")
    @JsonProperty("examstarttime")
    private String examStartTime;

    @Field("examendtime")
    @JsonProperty("examendtime")
    private String examEndTime;

    @Field("examLocation")
    @JsonProperty("examLocation")
    private String examLocation;

    @Field("asstring")
    @JsonProperty("asstring")
    private String asString;
}
