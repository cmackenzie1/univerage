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

package com.univerage.repository;

import com.univerage.uofa.model.Term;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "term", path = "term")
public interface TermRepository extends PagingAndSortingRepository<Term, String> {
    Term findTermById(@Param("id") String id);

    Term findTermByTerm(@Param("term") String term);

    List<Term> findTermsByTitleIgnoreCaseContaining(@Param("title") String title);

    List<Term> findTermsByStartDateAfter(@Param("startdate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startdate);
}
