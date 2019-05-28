package com.univerage.univerage.repository;

import com.univerage.univerage.model.Term;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TermRepository extends PagingAndSortingRepository<Term, Long> {
    Term findTermBySemesterIgnoreCaseAndYear(@Param("semester") String semester, @Param("year") int year);
}
