package com.philips.university.controller;

import com.philips.university.constants.AuthorizationConstants;
import com.philips.university.constants.LoggingConstants;
import com.philips.university.constants.ScheduleConstants;
import com.philips.university.dto.request.ScheduleRequestDto;
import com.philips.university.dto.response.ScheduleListResponseDto;
import com.philips.university.dto.response.ScheduleResponseDto;
import com.philips.university.dto.response.ScheduleSearchResponseDto;
import com.philips.university.service.ScheduleService;
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
@RequestMapping(ScheduleConstants.SCHEDULE_API_PATH)
public class ScheduleController {

    private static final Logger logger =
            LoggerFactory.getLogger(ScheduleController.class);

    private final ScheduleService scheduleService;

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
        final Optional<ScheduleResponseDto> scheduleResponseDto = scheduleService.createSchedule(scheduleRequestDto);
        logger.info(LoggingConstants.CREATE_SCHEDULE_LOG, scheduleResponseDto.get().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponseDto.get());
    }


    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @GetMapping(path = ScheduleConstants.SCHEDULE_ID_PATH)
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        final Optional<ScheduleResponseDto> scheduleResponseDto = scheduleService.getSchedule(id);
        if (scheduleResponseDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scheduleResponseDto.get());
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @PutMapping(path = ScheduleConstants.SCHEDULE_ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
        final Optional<ScheduleResponseDto> updatedSchedule =
                scheduleService.updateSchedule(id, scheduleRequestDto);
        if (updatedSchedule.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info(LoggingConstants.UPDATE_SCHEDULE_LOG, updatedSchedule.get().toString());
        return ResponseEntity.ok(updatedSchedule.get());
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @DeleteMapping(path = ScheduleConstants.SCHEDULE_ID_PATH)
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        logger.info(LoggingConstants.DELETE_SCHEDULE_LOG, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @GetMapping
    public ResponseEntity<ScheduleListResponseDto> getSchedules(
            @RequestParam(required = false, name = "page",
                    defaultValue = "0") int page,
            @RequestParam(required = false, name = "size",
                    defaultValue = "20") int size,
            @RequestParam(required = false, name = "sortField",
                    defaultValue = "name") String sortField,
            @RequestParam(required = false, name = "direction",
                    defaultValue = "ASC") String direction
    ) {
        final ScheduleListResponseDto responseDtoList =
                scheduleService.getScheduleList(page, size, sortField, direction);
        return ResponseEntity.ok(responseDtoList);
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @GetMapping(path = ScheduleConstants.SCHEDULE_SEARCH_API_PATH)
    public ResponseEntity<ScheduleSearchResponseDto> search() {
        final ScheduleSearchResponseDto responseDtoList =
                scheduleService.search();
        return ResponseEntity.ok(responseDtoList);
    }

}
