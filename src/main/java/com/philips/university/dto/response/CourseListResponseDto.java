package com.philips.university.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CourseListResponseDto {

    private List<CourseResponseDto> courseResponseDtoList;
}
