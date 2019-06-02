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

package com.univerage.univerage.model.mongo.factory;

import com.univerage.univerage.model.mongo.Instructor;

public class InstructorFactory extends BaseFactory<Instructor> {

    @Override
    public Instructor create() {
        return Instructor.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }
}
