package com.philips.university.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleListResponseDto {

    private List<ScheduleResponseDto> scheduleResponseDtoList;
}
