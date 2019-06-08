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

package com.univerage.univerage.uofa.model;

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
    private String _class;
    @Field("section")
    private String section;
    @Field("component")
    private String component;
    @Field("classtype")
    private String classType;
    @Field("classstatus")
    private String classStatus;
    @Field("enrollstatus")
    private String enrollStatus;
    @Field("capacity")
    private String capacity;
    @Field("startdate")
    private String startDate;
    @Field("enddate")
    private String endDate;
    @Field("session")
    private String session;
    @Field("campus")
    private String campus;
    @Field("location")
    private String location;
    @Field("autoenroll")
    private String autoEnroll;
    @Field("classtopic")
    private String classTopic;
    @Field("classnotes")
    private String classNotes;
    @Field("consent")
    private String consent;
    @Field("gradingbasis")
    private String gradingBasis;
    @Field("instructionMode")
    private String instructionMode;
    @Field("units")
    private String units;
    @Field("classurl")
    private String classUrl;
    @Field("instructoruid")
    private String instructorUid;
    @Field("examstatus")
    private String examStatus;
    @Field("examdate")
    private String examDate;
    @Field("examstarttime")
    private String examStartTime;
    @Field("examendtime")
    private String examEndTime;
    @Field("examLocation")
    private String examLocation;
    @Field("asstring")
    private String asString;
}
