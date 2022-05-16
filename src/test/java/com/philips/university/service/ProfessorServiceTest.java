package com.philips.university.service;

import com.philips.university.dto.request.ProfessorRequestDto;
import com.philips.university.dto.response.ProfessorResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProfessorServiceTest {

    ProfessorRequestDto professorRequestDto = new ProfessorRequestDto("James Hetfield", 1l);
    ProfessorRequestDto updatedProfessorRequestDto = new ProfessorRequestDto("Kirk Hammett", 1l);
    ProfessorResponseDto professorResponseDto = new ProfessorResponseDto();

    @Autowired
    private ProfessorService professorService;

    @Test
    void testCreateProfessorSuccess() {
        professorResponseDto.setName("James Hetfield");
        Optional<ProfessorResponseDto> optionalProfessorResponseDto = professorService.createProfessor(professorRequestDto);
        assertEquals(optionalProfessorResponseDto.get().getName(), professorResponseDto.getName());
    }

    @Test
    void testGetProfessorSuccess() {
        Optional<ProfessorResponseDto> expectedProfessorResponseDto = professorService.createProfessor(professorRequestDto);
        Optional<ProfessorResponseDto> optionalProfessorResponseDto = professorService.getProfessor(expectedProfessorResponseDto.get().getId());
        assertEquals(optionalProfessorResponseDto.get().getId(), expectedProfessorResponseDto.get().getId());
    }

    @Test
    void testUpdateProfessorSuccess() {
        professorResponseDto.setName("James Hetfield");
        Optional<ProfessorResponseDto> optionalProfessorResponseDto = professorService.updateProfessor(1L, updatedProfessorRequestDto);
        assertNotEquals(optionalProfessorResponseDto.get().getName(), professorResponseDto.getName());
    }

    @Test
    void testDeleteProfessorSuccess() {
        Optional<ProfessorResponseDto> expectedProfessorResponseDto = professorService.createProfessor(updatedProfessorRequestDto);
        professorService.deleteProfessor(expectedProfessorResponseDto.get().getId());
        Optional<ProfessorResponseDto> optionalProfessorResponseDto = professorService.getProfessor(expectedProfessorResponseDto.get().getId());
        assertFalse(optionalProfessorResponseDto.isPresent());
    }
}
