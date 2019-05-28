package com.univerage.univerage.repository;

import com.univerage.univerage.model.Instructor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "instructor", path = "instructor")
public interface InstructorRepository extends PagingAndSortingRepository<Instructor, Long> {

    List<Instructor> findByLastName(@Param("name") String name);

}
