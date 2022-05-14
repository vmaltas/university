package com.philips.university.service;

import com.philips.university.dto.request.DepartmentRequestDto;
import com.philips.university.dto.response.DepartmentListResponseDto;
import com.philips.university.dto.response.DepartmentResponseDto;

import java.util.Optional;

public interface DepartmentService {

    Optional<DepartmentResponseDto> createDepartment(DepartmentRequestDto departmentRequestDto);

    Optional<DepartmentResponseDto> getDepartment(Long id);

    Optional<DepartmentResponseDto> updateDepartment(Long id, DepartmentRequestDto departmentRequestDto);

    void deleteDepartment(Long id);

    DepartmentListResponseDto getDepartmentList(int page, int size, String sortField, String direction, String keyword);

}
