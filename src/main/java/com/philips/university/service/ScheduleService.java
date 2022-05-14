package com.philips.university.service;

import com.philips.university.dto.request.ScheduleRequestDto;
import com.philips.university.dto.response.ScheduleListResponseDto;
import com.philips.university.dto.response.ScheduleResponseDto;

import java.util.Optional;

public interface ScheduleService {

    Optional<ScheduleResponseDto> createSchedule(ScheduleRequestDto scheduleRequestDto);

    Optional<ScheduleResponseDto> getSchedule(Long id);

    Optional<ScheduleResponseDto> updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto);

    void deleteSchedule(Long id);

    ScheduleListResponseDto getScheduleList(int page, int size, String sortField, String direction);

}
