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

    @Query(value = "select cast(row_to_json(subs) as varchar) from (select * from ( select p2.name name, ( select array_to_json(array_agg(b)) from (select c.name course from schedule s left join course c on c.id = s.course_id where p2.id = s.professor_id group by c.name ) b ) as courses from professor p2) sub2 where courses is not null) subs", nativeQuery = true)
    List<String> search();

}