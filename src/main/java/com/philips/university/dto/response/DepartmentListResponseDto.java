package com.philips.university.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentListResponseDto {

    private List<DepartmentResponseDto> departmentResponseDtoList;
}
