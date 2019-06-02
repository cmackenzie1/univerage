package com.univerage.univerage.repository;

import com.univerage.univerage.model.mongo.Term;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "term", path = "term")
public interface TermRepository extends PagingAndSortingRepository<Term, Long> {
    Term findTermBySemesterIgnoreCaseAndYear(@Param("semester") String semester, @Param("year") int year);
}
