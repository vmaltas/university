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
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapStructMapperImpl implements MapStructMapper {


    @Override
    public Optional<DepartmentResponseDto> departmentEntityToDepartmentResponseDto(Department department) {
        if (department == null) {
            return Optional.empty();
        }
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        Optional<DepartmentResponseDto> opt = Optional.of(departmentResponseDto);
        departmentResponseDto.setId(department.getId());
        departmentResponseDto.setName(department.getName());
        return opt;

    }

    @Override
    public Department departmentRequestDtoToDepartmentEntity(DepartmentRequestDto departmentRequestDto) {

        if (departmentRequestDto == null) {
            return null;
        }
        Department department = new Department();
        department.setName(departmentRequestDto.getName());
        return department;

    }

    @Override
    public Optional<ProfessorResponseDto> professorEntityToProfessorResponseDto(Professor professor) {
        if (professor == null) {
            return Optional.empty();
        }
        ProfessorResponseDto professorResponseDto = new ProfessorResponseDto();
        Optional<ProfessorResponseDto> opt = Optional.of(professorResponseDto);
        professorResponseDto.setId(professor.getId());
        professorResponseDto.setName(professor.getName());
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto.setId(professor.getDepartment().getId());
        departmentResponseDto.setName(professor.getDepartment().getName());
        professorResponseDto.setDepartmentResponseDto(departmentResponseDto);
        return opt;

    }

    @Override
    public Professor professorRequestDtoToProfessorEntity(ProfessorRequestDto professorRequestDto) {

        if (professorRequestDto == null) {
            return null;
        }
        Professor professor = new Professor();
        professor.setName(professorRequestDto.getName());
        return professor;

    }


    @Override
    public Optional<CourseResponseDto> courseEntityToCourseResponseDto(Course course) {
        if (course == null) {
            return Optional.empty();
        }
        CourseResponseDto courseResponseDto = new CourseResponseDto();
        Optional<CourseResponseDto> opt = Optional.of(courseResponseDto);
        courseResponseDto.setId(course.getId());
        courseResponseDto.setName(course.getName());
        courseResponseDto.setCredit(course.getCredit());
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto.setId(course.getDepartment().getId());
        departmentResponseDto.setName(course.getDepartment().getName());
        courseResponseDto.setDepartmentResponseDto(departmentResponseDto);
        return opt;

    }

    @Override
    public Course courseRequestDtoToCourseEntity(CourseRequestDto courseRequestDto) {

        if (courseRequestDto == null) {
            return null;
        }
        Course course = new Course();
        course.setName(courseRequestDto.getName());
        return course;

    }


    @Override
    public Optional<ScheduleResponseDto> scheduleEntityToScheduleResponseDto(Schedule schedule) {
        if (schedule == null) {
            return Optional.empty();
        }
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto();
        Optional<ScheduleResponseDto> opt = Optional.of(scheduleResponseDto);
        scheduleResponseDto.setId(schedule.getId());
        scheduleResponseDto.setSemester(schedule.getSemester());
        scheduleResponseDto.setYear(schedule.getYear());

        ProfessorResponseDto professorResponseDto = new ProfessorResponseDto();
        professorResponseDto.setId(schedule.getProfessor().getId());
        professorResponseDto.setName(schedule.getProfessor().getName());
        scheduleResponseDto.setProfessorResponseDto(professorResponseDto);

        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setId(schedule.getCourse().getId());
        courseResponseDto.setName(schedule.getCourse().getName());
        scheduleResponseDto.setCourseResponseDto(courseResponseDto);

        return opt;

    }

    @Override
    public Schedule scheduleRequestDtoToScheduleEntity(ScheduleRequestDto scheduleRequestDto) {

        if (scheduleRequestDto == null) {
            return null;
        }
        Schedule schedule = new Schedule();
        schedule.setSemester(scheduleRequestDto.getSemester());
        schedule.setYear(scheduleRequestDto.getYear());
        return schedule;

    }
}
