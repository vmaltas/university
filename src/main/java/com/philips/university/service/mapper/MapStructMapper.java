package com.philips.university.service.mapper;

import com.philips.university.domain.Course;
import com.philips.university.domain.Department;
import com.philips.university.domain.Professor;
import com.philips.university.domain.Schedule;
import com.philips.university.dto.request.CourseRequestDto;
import com.philips.university.dto.request.DepartmentRequestDto;
import com.philips.university.dto.request.ProfessorRequestDto;
import com.philips.university.dto.request.ScheduleRequestDto;
import com.philips.university.dto.response.CourseResponseDto;
import com.philips.university.dto.response.DepartmentResponseDto;
import com.philips.university.dto.response.ProfessorResponseDto;
import com.philips.university.dto.response.ScheduleResponseDto;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {

    Optional<DepartmentResponseDto> departmentEntityToDepartmentResponseDto(Department department);

    Department departmentRequestDtoToDepartmentEntity(DepartmentRequestDto departmentRequestDto);

    Optional<ProfessorResponseDto> professorEntityToProfessorResponseDto(Professor professor);

    Professor professorRequestDtoToProfessorEntity(ProfessorRequestDto professorRequestDto);

    Optional<CourseResponseDto> courseEntityToCourseResponseDto(Course course);

    Course courseRequestDtoToCourseEntity(CourseRequestDto courseRequestDto);

    Optional<ScheduleResponseDto> scheduleEntityToScheduleResponseDto(Schedule schedule);

    Schedule scheduleRequestDtoToScheduleEntity(ScheduleRequestDto scheduleRequestDto);
}
