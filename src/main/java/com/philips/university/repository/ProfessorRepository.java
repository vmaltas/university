package com.philips.university.repository;

import com.philips.university.domain.Professor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {
}