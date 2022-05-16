package com.philips.university.service;


import com.philips.university.domain.Course;
import com.philips.university.domain.Professor;
import com.philips.university.domain.Schedule;
import com.philips.university.dto.request.ScheduleRequestDto;
import com.philips.university.dto.response.ScheduleListResponseDto;
import com.philips.university.dto.response.ScheduleResponseDto;
import com.philips.university.dto.response.ScheduleSearchResponseDto;
import com.philips.university.repository.CourseRepository;
import com.philips.university.repository.ProfessorRepository;
import com.philips.university.repository.ScheduleRepository;
import com.philips.university.service.mapper.MapStructMapper;
import com.philips.university.util.SortUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final MapStructMapper mapstructMapper;

    private final ScheduleRepository scheduleRepository;

    private final ProfessorRepository professorRepository;

    private final CourseRepository courseRepository;

    public Optional<ScheduleResponseDto> createSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = mapstructMapper.scheduleRequestDtoToScheduleEntity(scheduleRequestDto);
        Professor professor = professorRepository.findById(scheduleRequestDto.getProfessorId()).orElse(null);
        schedule.setProfessor(professor);
        Course course = courseRepository.findById(scheduleRequestDto.getCourseId()).orElse(null);
        schedule.setCourse(course);
        return mapstructMapper.scheduleEntityToScheduleResponseDto(scheduleRepository.save(schedule));
    }

    public Optional<ScheduleResponseDto> getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        return mapstructMapper.scheduleEntityToScheduleResponseDto(schedule);
    }

    public Optional<ScheduleResponseDto> updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        if (schedule != null) {
            schedule.setSemester(scheduleRequestDto.getSemester());
            schedule.setYear(scheduleRequestDto.getYear());
            Professor professor = professorRepository.findById(scheduleRequestDto.getProfessorId()).orElse(null);
            schedule.setProfessor(professor);
            Course course = courseRepository.findById(scheduleRequestDto.getCourseId()).orElse(null);
            schedule.setCourse(course);
        }
        return mapstructMapper.scheduleEntityToScheduleResponseDto(scheduleRepository.save(schedule));
    }

    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        if (schedule != null) {
            scheduleRepository.delete(schedule);
        }
    }


    public ScheduleListResponseDto getScheduleList(int page, int size, String sortField, String direction) {
        List<Schedule> scheduleList;
        Sort.Order sortOrder = new Sort.Order(SortUtil.sortDirection(direction), sortField);
        scheduleList = scheduleRepository.findAllSchedules(PageRequest.of(page, size, Sort.by(sortOrder)));

        ScheduleListResponseDto scheduleListResponseDto = new ScheduleListResponseDto();
        List<ScheduleResponseDto> responseDtoList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            Optional<ScheduleResponseDto> scheduleResponseDto = mapstructMapper.scheduleEntityToScheduleResponseDto(schedule);
            responseDtoList.add(scheduleResponseDto.orElse(null));
        }
        scheduleListResponseDto.setScheduleResponseDtoList(responseDtoList);
        return scheduleListResponseDto;
    }

    public ScheduleSearchResponseDto search() {
        List<String> searchList = scheduleRepository.search();
        ScheduleSearchResponseDto scheduleSearchResponseDto = new ScheduleSearchResponseDto();
        scheduleSearchResponseDto.setSearchResponseDto(searchList);
        return scheduleSearchResponseDto;
    }


}
