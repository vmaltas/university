package com.philips.university.service;

import com.philips.university.dto.request.ScheduleRequestDto;
import com.philips.university.dto.response.ScheduleResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleServiceTest {

    ScheduleRequestDto courseRequestDto = new ScheduleRequestDto(1l, 1l, 3, 2012);
    ScheduleRequestDto updatedScheduleRequestDto = new ScheduleRequestDto(1l, 1l, 4, 2013);
    ScheduleResponseDto courseResponseDto = new ScheduleResponseDto();

    @Autowired
    private ScheduleService courseService;

    @Test
    void testCreateScheduleSuccess() {
        courseResponseDto.setSemester(3);
        courseResponseDto.setYear(2012);
        Optional<ScheduleResponseDto> optionalScheduleResponseDto = courseService.createSchedule(courseRequestDto);
        assertEquals(optionalScheduleResponseDto.get().getSemester(), courseResponseDto.getSemester());
        assertEquals(optionalScheduleResponseDto.get().getYear(), courseResponseDto.getYear());
    }

    @Test
    void testGetScheduleSuccess() {
        Optional<ScheduleResponseDto> expectedScheduleResponseDto = courseService.createSchedule(courseRequestDto);
        Optional<ScheduleResponseDto> optionalScheduleResponseDto = courseService.getSchedule(expectedScheduleResponseDto.get().getId());
        assertEquals(optionalScheduleResponseDto.get().getId(), expectedScheduleResponseDto.get().getId());
    }

    @Test
    void testUpdateScheduleSuccess() {
        courseResponseDto.setSemester(3);
        courseResponseDto.setYear(2012);
        Optional<ScheduleResponseDto> optionalScheduleResponseDto = courseService.updateSchedule(1L, updatedScheduleRequestDto);
        assertNotEquals(optionalScheduleResponseDto.get().getSemester(), courseResponseDto.getSemester());
        assertNotEquals(optionalScheduleResponseDto.get().getYear(), courseResponseDto.getYear());
    }

    @Test
    void testDeleteScheduleSuccess() {
        Optional<ScheduleResponseDto> expectedScheduleResponseDto = courseService.createSchedule(updatedScheduleRequestDto);
        courseService.deleteSchedule(expectedScheduleResponseDto.get().getId());
        Optional<ScheduleResponseDto> optionalScheduleResponseDto = courseService.getSchedule(expectedScheduleResponseDto.get().getId());
        assertFalse(optionalScheduleResponseDto.isPresent());
    }
}
