package com.philips.university.controller;

import com.philips.university.constants.AuthorizationConstants;
import com.philips.university.constants.DepartmentConstants;
import com.philips.university.constants.LoggingConstants;
import com.philips.university.dto.request.DepartmentRequestDto;
import com.philips.university.dto.response.DepartmentListResponseDto;
import com.philips.university.dto.response.DepartmentResponseDto;
import com.philips.university.service.DepartmentService;
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
@RequestMapping(DepartmentConstants.DEPARTMENT_API_PATH)
public class DepartmentController {

    private static final Logger logger =
            LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
        final Optional<DepartmentResponseDto> departmentResponseDto = departmentService.createDepartment(departmentRequestDto);
        logger.info(LoggingConstants.CREATE_DEPARTMENT_LOG, departmentResponseDto.<Object>map(DepartmentResponseDto::toString).orElse(null));
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentResponseDto.orElse(null));
    }


    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @GetMapping(path = DepartmentConstants.DEPARTMENT_ID_PATH)
    public ResponseEntity<DepartmentResponseDto> getDepartment(@PathVariable Long id) {
        final Optional<DepartmentResponseDto> departmentResponseDto = departmentService.getDepartment(id);
        if (departmentResponseDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(departmentResponseDto.get());
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @PutMapping(path = DepartmentConstants.DEPARTMENT_ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
        final Optional<DepartmentResponseDto> updatedDepartment =
                departmentService.updateDepartment(id, departmentRequestDto);
        if (updatedDepartment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info(LoggingConstants.UPDATE_DEPARTMENT_LOG, updatedDepartment.<Object>map(DepartmentResponseDto::toString).orElse(null));
        return ResponseEntity.ok(updatedDepartment.get());
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @DeleteMapping(path = DepartmentConstants.DEPARTMENT_ID_PATH)
    public ResponseEntity<DepartmentResponseDto> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        logger.info(LoggingConstants.DELETE_DEPARTMENT_LOG, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @GetMapping
    public ResponseEntity<DepartmentListResponseDto> getDepartments(
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
        final DepartmentListResponseDto responseDtoList =
                departmentService.getDepartmentList(page, size, sortField, direction, keyword);
        return ResponseEntity.ok(responseDtoList);
    }


}
