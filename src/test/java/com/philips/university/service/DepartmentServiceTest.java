package com.philips.university.service;

import com.philips.university.dto.request.DepartmentRequestDto;
import com.philips.university.dto.response.DepartmentResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DepartmentServiceTest {

    DepartmentRequestDto departmentRequestDto = new DepartmentRequestDto("Department of Magic");
    DepartmentRequestDto updatedDepartmentRequestDto = new DepartmentRequestDto("Department of Black Magic");
    DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();

    @Autowired
    private DepartmentService departmentService;

    @Test
    void givenDepartmentObject_whenSaveDepartment_thenReturnDepartmentObject() {
        departmentResponseDto.setName("Department of Magic");
        Optional<DepartmentResponseDto> optionalDepartmentResponseDto = departmentService.createDepartment(departmentRequestDto);
        assertEquals(optionalDepartmentResponseDto.get().getName(), departmentResponseDto.getName());
    }

    @Test
    void testGetDepartmentSuccess() {
        Optional<DepartmentResponseDto> expectedDepartmentResponseDto = departmentService.createDepartment(departmentRequestDto);
        Optional<DepartmentResponseDto> optionalDepartmentResponseDto = departmentService.getDepartment(expectedDepartmentResponseDto.get().getId());
        assertEquals(optionalDepartmentResponseDto.get().getId(), expectedDepartmentResponseDto.get().getId());
    }

    @Test
    void testUpdateDepartmentSuccess() {
        departmentResponseDto.setName("Department of Magic");
        Optional<DepartmentResponseDto> optionalDepartmentResponseDto = departmentService.updateDepartment(2L, updatedDepartmentRequestDto);
        assertNotEquals(optionalDepartmentResponseDto.get().getName(), departmentResponseDto.getName());
    }

    @Test
    void testDeleteDepartmentSuccess() {
        Optional<DepartmentResponseDto> expectedDepartmentResponseDto = departmentService.createDepartment(updatedDepartmentRequestDto);
        departmentService.deleteDepartment(expectedDepartmentResponseDto.get().getId());
        Optional<DepartmentResponseDto> optionalDepartmentResponseDto = departmentService.getDepartment(expectedDepartmentResponseDto.get().getId());
        assertFalse(optionalDepartmentResponseDto.isPresent());
    }
}
