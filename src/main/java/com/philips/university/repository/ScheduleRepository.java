package com.philips.university.repository;

import com.philips.university.domain.Schedule;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {

    @Query("from Schedule")
    List<Schedule> findAllSchedules(Pageable pageable);
}