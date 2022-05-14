package com.philips.university.repository;

import com.philips.university.domain.Schedule;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {
}