package com.philips.university.service;

import com.philips.university.dto.request.CourseRequestDto;
import com.philips.university.dto.response.CourseResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CourseServiceTest {

    CourseRequestDto courseRequestDto = new CourseRequestDto("Lead Guitar-101", 1, 1l);
    CourseRequestDto updatedCourseRequestDto = new CourseRequestDto("Solo Guitar-101", 1, 1l);
    CourseResponseDto courseResponseDto = new CourseResponseDto();

    @Autowired
    private CourseService courseService;

    @Test
    void testCreateCourseSuccess() {
        courseResponseDto.setName("Lead Guitar-101");
        Optional<CourseResponseDto> optionalCourseResponseDto = courseService.createCourse(courseRequestDto);
        assertEquals(optionalCourseResponseDto.get().getName(), courseResponseDto.getName());
    }

    @Test
    void testGetCourseSuccess() {
        Optional<CourseResponseDto> expectedCourseResponseDto = courseService.createCourse(courseRequestDto);
        Optional<CourseResponseDto> optionalCourseResponseDto = courseService.getCourse(expectedCourseResponseDto.get().getId());
        assertEquals(optionalCourseResponseDto.get().getId(), expectedCourseResponseDto.get().getId());
    }

    @Test
    void testUpdateCourseSuccess() {
        courseResponseDto.setName("Lead Guitar-101");
        Optional<CourseResponseDto> optionalCourseResponseDto = courseService.updateCourse(1L, updatedCourseRequestDto);
        assertNotEquals(optionalCourseResponseDto.get().getName(), courseResponseDto.getName());
    }

    @Test
    void testDeleteCourseSuccess() {
        Optional<CourseResponseDto> expectedCourseResponseDto = courseService.createCourse(updatedCourseRequestDto);
        courseService.deleteCourse(expectedCourseResponseDto.get().getId());
        Optional<CourseResponseDto> optionalCourseResponseDto = courseService.getCourse(expectedCourseResponseDto.get().getId());
        assertFalse(optionalCourseResponseDto.isPresent());
    }
}
