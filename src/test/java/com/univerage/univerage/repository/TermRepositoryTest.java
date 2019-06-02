package com.univerage.univerage.repository;

import com.univerage.univerage.model.mongo.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class TermRepositoryTest {

    @Autowired
    private TermRepository termRepository;

    @Test
    void whenFindTermBySemesterAndYear_thenReturnTerm(@Autowired MongoTemplate mongoTemplate) {
        // given
        Term fall2016 = Term.builder().semester("Fall").year(2016).build();

        mongoTemplate.save(fall2016);

        // when
        Term found = termRepository.findTermBySemesterIgnoreCaseAndYear("fall", 2016);

        // then
        assertThat(found.getSemester()).isEqualToIgnoringCase("fall");
        assertThat(found.getYear()).isEqualTo(2016);
    }

}