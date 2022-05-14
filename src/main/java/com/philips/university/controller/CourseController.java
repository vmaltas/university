package com.philips.university.controller;

import com.philips.university.constants.AuthorizationConstants;
import com.philips.university.constants.CourseConstants;
import com.philips.university.constants.LoggingConstants;
import com.philips.university.dto.request.CourseRequestDto;
import com.philips.university.dto.response.CourseListResponseDto;
import com.philips.university.dto.response.CourseResponseDto;
import com.philips.university.service.CourseService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping(CourseConstants.COURSE_API_PATH)
public class CourseController {

    private static final Logger logger =
            LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseResponseDto> createCourse(
            @Valid @RequestBody CourseRequestDto courseRequestDto) {
        final Optional<CourseResponseDto> courseResponseDto = courseService.createCourse(courseRequestDto);
        logger.info(LoggingConstants.CREATE_COURSE_LOG, courseResponseDto.get().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponseDto.get());
    }


    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @GetMapping(path = CourseConstants.COURSE_ID_PATH)
    public ResponseEntity<CourseResponseDto> getCourse(@PathVariable Long id) {
        final Optional<CourseResponseDto> courseResponseDto = courseService.getCourse(id);
        if (courseResponseDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseResponseDto.get());
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @PutMapping(path = CourseConstants.COURSE_ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseResponseDto> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDto courseRequestDto) {
        final Optional<CourseResponseDto> updatedCourse =
                courseService.updateCourse(id, courseRequestDto);
        if (updatedCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info(LoggingConstants.UPDATE_COURSE_LOG, updatedCourse.get().toString());
        return ResponseEntity.ok(updatedCourse.get());
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @DeleteMapping(path = CourseConstants.COURSE_ID_PATH)
    public ResponseEntity<CourseResponseDto> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        logger.info(LoggingConstants.DELETE_COURSE_LOG, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @GetMapping
    public ResponseEntity<CourseListResponseDto> getCourses(
            @RequestParam(required = false, name = "page",
                    defaultValue = "0") int page,
            @RequestParam(required = false, name = "size",
                    defaultValue = "20") int size,
            @RequestParam(required = false, name = "sortField",
                    defaultValue = "name") String sortField,
            @RequestParam(required = false, name = "direction",
                    defaultValue = "ASC") String direction,
            @RequestParam(required = false, name = "keyword") String keyword
    ) {
        final CourseListResponseDto responseDtoList =
                courseService.getCourseList(page, size, sortField, direction, keyword);
        return ResponseEntity.ok(responseDtoList);
    }

}
