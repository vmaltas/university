package com.philips.university.repository;

import com.philips.university.domain.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

    @Query("from Department")
    List<Department> findAllDepartments(Pageable pageable);

    List<Department> findByNameIsContaining(@Param("keyword") String keyword, Pageable pageable);

}