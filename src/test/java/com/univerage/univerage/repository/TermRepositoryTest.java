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

import com.univerage.univerage.uofa.model.Term;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class TermRepositoryTest {

    @Autowired
    private TermRepository termRepository;

    @Test
    void whenFindTermById_returnTerm() {
        Term term = Term.builder()
                .term("1700")
                .startDate(LocalDate.of(2020, 1, 6))
                .endDate(LocalDate.of(2020, 4, 8))
                .title("Winter Term 2020")
                .build();

        termRepository.deleteAll();
        termRepository.save(term);

        Term result = termRepository.findTermById(term.getId());
        assertThat(result).isEqualToIgnoringGivenFields(term, "id");
    }

    @Test
    void whenFindTermByTerm_returnTerm() {
        Term term = Term.builder()
                .term("1700")
                .startDate(LocalDate.of(2020, 1, 6))
                .endDate(LocalDate.of(2020, 4, 8))
                .title("Winter Term 2020")
                .build();

        termRepository.deleteAll();
        termRepository.save(term);

        Term result = termRepository.findTermByTerm(term.getTerm());
        assertThat(result).isEqualToIgnoringGivenFields(term, "id");
    }

    @Test
    void whenFindTermsByTitleIgnoreCaseContaining_returnTerms() {
        Term term = Term.builder()
                .term("1700")
                .startDate(LocalDate.of(2020, 1, 6))
                .endDate(LocalDate.of(2020, 4, 8))
                .title("Winter Term 2020")
                .build();

        Term term2 = Term.builder()
                .term("1800")
                .startDate(LocalDate.of(2021, 1, 6))
                .endDate(LocalDate.of(2021, 4, 8))
                .title("Winter Term 2021")
                .build();

        termRepository.deleteAll();
        termRepository.save(term);
        termRepository.save(term2);

        List<Term> results = termRepository.findTermsByTitleIgnoreCaseContaining("WiNtEr");
        assertThat(results).hasSize(2);
    }

    @Test
    void whenFindTermsByStartDateAfter_returnTerms() {
        Term term = Term.builder()
                .term("1700")
                .startDate(LocalDate.of(2020, 1, 6))
                .endDate(LocalDate.of(2020, 4, 8))
                .title("Winter Term 2020")
                .build();

        Term term2 = Term.builder()
                .term("1800")
                .startDate(LocalDate.of(2021, 1, 6))
                .endDate(LocalDate.of(2021, 4, 8))
                .title("Winter Term 2021")
                .build();

        termRepository.deleteAll();
        termRepository.save(term);
        termRepository.save(term2);

        List<Term> results;
        results = termRepository.findTermsByStartDateAfter(LocalDate.of(2021, 1, 1));
        assertThat(results).hasSize(1);
        results = termRepository.findTermsByStartDateAfter(LocalDate.of(1970, 1, 1));
        assertThat(results).hasSize(2);
    }

}