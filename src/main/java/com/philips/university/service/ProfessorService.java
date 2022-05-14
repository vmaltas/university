package com.philips.university.service;

import com.philips.university.dto.request.ProfessorRequestDto;
import com.philips.university.dto.response.ProfessorListResponseDto;
import com.philips.university.dto.response.ProfessorResponseDto;

import java.util.Optional;

public interface ProfessorService {

    Optional<ProfessorResponseDto> createProfessor(ProfessorRequestDto professorRequestDto);

    Optional<ProfessorResponseDto> getProfessor(Long id);

    Optional<ProfessorResponseDto> updateProfessor(Long id, ProfessorRequestDto professorRequestDto);

    void deleteProfessor(Long id);

    ProfessorListResponseDto getProfessorList(int page, int size, String sortField, String direction, String keyword);

}
