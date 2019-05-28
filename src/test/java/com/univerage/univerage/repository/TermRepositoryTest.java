package com.univerage.univerage.repository;

import com.univerage.univerage.model.Instructor;
import com.univerage.univerage.model.Term;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TermRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TermRepository termRepository;

    @Test
    public void whenFindTermBySemesterAndYear_thenReturnTerm() {
        // given
        Term fall2016 = Term.builder().semester("Fall").year(2016).build();

        entityManager.persistAndFlush(fall2016);

        // when
        Term found = termRepository.findTermBySemesterIgnoreCaseAndYear("fall", 2016);

        // then
        assertThat(found.getSemester()).isEqualToIgnoringCase("fall");
        assertThat(found.getYear()).isEqualTo(2016);
    }

}