package com.philips.university.repository;

import com.philips.university.domain.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @Query("from Course")
    List<Course> findAllCourses(Pageable pageable);

    List<Course> findByNameIsContaining(@Param("keyword") String keyword, Pageable pageable);

}