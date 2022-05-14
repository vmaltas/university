package com.philips.university.service;


import com.philips.university.domain.Course;
import com.philips.university.dto.request.CourseRequestDto;
import com.philips.university.dto.response.CourseListResponseDto;
import com.philips.university.dto.response.CourseResponseDto;
import com.philips.university.repository.CourseRepository;
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
public class CourseServiceImpl implements CourseService {

    private final MapStructMapper mapstructMapper;

    private final CourseRepository courseRepository;

    public Optional<CourseResponseDto> createCourse(CourseRequestDto courseRequestDto) {
        Course course = mapstructMapper.courseRequestDtoToCourseEntity(courseRequestDto);
        return mapstructMapper.courseEntityToCourseResponseDto(courseRepository.save(course));
    }

    public Optional<CourseResponseDto> getCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        return mapstructMapper.courseEntityToCourseResponseDto(course);
    }

    public Optional<CourseResponseDto> updateCourse(Long id, CourseRequestDto courseRequestDto) {
        Course course = courseRepository.findById(id).orElse(null);
        course.setName(courseRequestDto.getName());
        return mapstructMapper.courseEntityToCourseResponseDto(courseRepository.save(course));
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        courseRepository.delete(course);
    }


    public CourseListResponseDto getCourseList(int page, int size, String sortField, String direction, String keyword) {
        List<Course> courseList;
        Sort.Order sortOrder = new Sort.Order(SortUtil.sortDirection(direction), sortField);
        if (keyword != null) {
            courseList = courseRepository.findByNameIsContaining(keyword, PageRequest.of(page, size, Sort.by(sortOrder)));
        } else {
            courseList = courseRepository.findAllCourses(PageRequest.of(page, size, Sort.by(sortOrder)));
        }


        CourseListResponseDto courseListResponseDto = new CourseListResponseDto();
        List<CourseResponseDto> responseDtoList = new ArrayList<>();
        for (Course course : courseList) {
            responseDtoList.add(mapstructMapper.courseEntityToCourseResponseDto(course).get());
        }
        courseListResponseDto.setCourseResponseDtoList(responseDtoList);
        return courseListResponseDto;
    }


}
