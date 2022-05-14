package com.philips.university.service;

import com.philips.university.dto.request.CourseRequestDto;
import com.philips.university.dto.response.CourseListResponseDto;
import com.philips.university.dto.response.CourseResponseDto;

import java.util.Optional;

public interface CourseService {

    Optional<CourseResponseDto> createCourse(CourseRequestDto courseRequestDto);

    Optional<CourseResponseDto> getCourse(Long id);

    Optional<CourseResponseDto> updateCourse(Long id, CourseRequestDto courseRequestDto);

    void deleteCourse(Long id);

    CourseListResponseDto getCourseList(int page, int size, String sortField, String direction, String keyword);

}
