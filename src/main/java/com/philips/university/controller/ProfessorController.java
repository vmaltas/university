package com.philips.university.controller;

import com.philips.university.constants.AuthorizationConstants;
import com.philips.university.constants.LoggingConstants;
import com.philips.university.constants.ProfessorConstants;
import com.philips.university.dto.request.ProfessorRequestDto;
import com.philips.university.dto.response.ProfessorListResponseDto;
import com.philips.university.dto.response.ProfessorResponseDto;
import com.philips.university.service.ProfessorService;
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
@RequestMapping(ProfessorConstants.PROFESSOR_API_PATH)
public class ProfessorController {

    private static final Logger logger =
            LoggerFactory.getLogger(ProfessorController.class);

    private final ProfessorService professorService;

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessorResponseDto> createProfessor(
            @Valid @RequestBody ProfessorRequestDto professorRequestDto) {
        final Optional<ProfessorResponseDto> professorResponseDto = professorService.createProfessor(professorRequestDto);
        logger.info(LoggingConstants.CREATE_PROFESSOR_LOG, professorResponseDto.<Object>map(ProfessorResponseDto::toString).orElse(null));
        return ResponseEntity.status(HttpStatus.CREATED).body(professorResponseDto.orElse(null));
    }


    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @GetMapping(path = ProfessorConstants.PROFESSOR_ID_PATH)
    public ResponseEntity<ProfessorResponseDto> getProfessor(@PathVariable Long id) {
        final Optional<ProfessorResponseDto> professorResponseDto = professorService.getProfessor(id);
        if (professorResponseDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professorResponseDto.get());
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @PutMapping(path = ProfessorConstants.PROFESSOR_ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessorResponseDto> updateProfessor(
            @PathVariable Long id,
            @Valid @RequestBody ProfessorRequestDto professorRequestDto) {
        final Optional<ProfessorResponseDto> updatedProfessor =
                professorService.updateProfessor(id, professorRequestDto);
        if (updatedProfessor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info(LoggingConstants.UPDATE_PROFESSOR_LOG, updatedProfessor.<Object>map(ProfessorResponseDto::toString).orElse(null));
        return ResponseEntity.ok(updatedProfessor.get());
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @DeleteMapping(path = ProfessorConstants.PROFESSOR_ID_PATH)
    public ResponseEntity<ProfessorResponseDto> deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        logger.info(LoggingConstants.DELETE_PROFESSOR_LOG, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(AuthorizationConstants.HAS_ADMIN_ROLE)
    @GetMapping
    public ResponseEntity<ProfessorListResponseDto> getProfessors(
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
        final ProfessorListResponseDto responseDtoList =
                professorService.getProfessorList(page, size, sortField, direction, keyword);
        return ResponseEntity.ok(responseDtoList);
    }

}
