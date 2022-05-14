package com.philips.university.service;

import com.philips.university.domain.Department;
import com.philips.university.dto.request.DepartmentRequestDto;
import com.philips.university.dto.response.DepartmentListResponseDto;
import com.philips.university.dto.response.DepartmentResponseDto;
import com.philips.university.repository.DepartmentRepository;
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
public class DepartmentServiceImpl implements DepartmentService {

    private final MapStructMapper mapstructMapper;

    private final DepartmentRepository departmentRepository;

    public Optional<DepartmentResponseDto> createDepartment(DepartmentRequestDto departmentRequestDto) {
        Department department = mapstructMapper.departmentRequestDtoToDepartmentEntity(departmentRequestDto);
        return mapstructMapper.departmentEntityToDepartmentResponseDto(departmentRepository.save(department));
    }

    public Optional<DepartmentResponseDto> getDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        return mapstructMapper.departmentEntityToDepartmentResponseDto(department);
    }

    public Optional<DepartmentResponseDto> updateDepartment(Long id, DepartmentRequestDto departmentRequestDto) {
        Department department = departmentRepository.findById(id).orElse(null);
        department.setName(departmentRequestDto.getName());
        return mapstructMapper.departmentEntityToDepartmentResponseDto(departmentRepository.save(department));
    }

    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        departmentRepository.delete(department);
    }


    public DepartmentListResponseDto getDepartmentList(int page, int size, String sortField, String direction, String keyword) {
        List<Department> departmentList;
        Sort.Order sortOrder = new Sort.Order(SortUtil.sortDirection(direction), sortField);
        if (keyword != null) {
            departmentList = departmentRepository.findByNameIsContaining(keyword, PageRequest.of(page, size, Sort.by(sortOrder)));
        } else {
            departmentList = departmentRepository.findAllDepartments(PageRequest.of(page, size, Sort.by(sortOrder)));
        }


        DepartmentListResponseDto departmentListResponseDto = new DepartmentListResponseDto();
        List<DepartmentResponseDto> responseDtoList = new ArrayList<>();
        for (Department department : departmentList) {
            responseDtoList.add(mapstructMapper.departmentEntityToDepartmentResponseDto(department).get());
        }
        departmentListResponseDto.setDepartmentResponseDtoList(responseDtoList);
        return departmentListResponseDto;
    }


}
