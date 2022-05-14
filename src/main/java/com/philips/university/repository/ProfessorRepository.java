package com.philips.university.repository;

import com.philips.university.domain.Professor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {

    @Query("from Professor")
    List<Professor> findAllProfessors(Pageable pageable);

    List<Professor> findByNameIsContaining(@Param("keyword") String keyword, Pageable pageable);

}