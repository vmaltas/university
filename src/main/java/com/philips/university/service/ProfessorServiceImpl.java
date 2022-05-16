package com.philips.university.service;


import com.philips.university.domain.Department;
import com.philips.university.domain.Professor;
import com.philips.university.dto.request.ProfessorRequestDto;
import com.philips.university.dto.response.ProfessorListResponseDto;
import com.philips.university.dto.response.ProfessorResponseDto;
import com.philips.university.repository.DepartmentRepository;
import com.philips.university.repository.ProfessorRepository;
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
public class ProfessorServiceImpl implements ProfessorService {

    private final MapStructMapper mapstructMapper;

    private final ProfessorRepository professorRepository;

    private final DepartmentRepository departmentRepository;

    public Optional<ProfessorResponseDto> createProfessor(ProfessorRequestDto professorRequestDto) {
        Professor professor = mapstructMapper.professorRequestDtoToProfessorEntity(professorRequestDto);
        Department department = departmentRepository.findById(professorRequestDto.getDepartmentId()).orElse(null);
        professor.setDepartment(department);
        return mapstructMapper.professorEntityToProfessorResponseDto(professorRepository.save(professor));
    }

    public Optional<ProfessorResponseDto> getProfessor(Long id) {
        Professor professor = professorRepository.findById(id).orElse(null);
        return mapstructMapper.professorEntityToProfessorResponseDto(professor);
    }

    public Optional<ProfessorResponseDto> updateProfessor(Long id, ProfessorRequestDto professorRequestDto) {
        Professor professor = professorRepository.findById(id).orElse(null);
        if (professor != null) {
            professor.setName(professorRequestDto.getName());
            Department department = departmentRepository.findById(professorRequestDto.getDepartmentId()).orElse(null);
            professor.setDepartment(department);
        }
        return mapstructMapper.professorEntityToProfessorResponseDto(professorRepository.save(professor));
    }

    public void deleteProfessor(Long id) {
        Professor professor = professorRepository.findById(id).orElse(null);
        if (professor != null) {
            professorRepository.delete(professor);
        }
    }


    public ProfessorListResponseDto getProfessorList(int page, int size, String sortField, String direction, String keyword) {
        List<Professor> professorList;
        Sort.Order sortOrder = new Sort.Order(SortUtil.sortDirection(direction), sortField);
        if (keyword != null) {
            professorList = professorRepository.findByNameIsContaining(keyword, PageRequest.of(page, size, Sort.by(sortOrder)));
        } else {
            professorList = professorRepository.findAllProfessors(PageRequest.of(page, size, Sort.by(sortOrder)));
        }


        ProfessorListResponseDto professorListResponseDto = new ProfessorListResponseDto();
        List<ProfessorResponseDto> responseDtoList = new ArrayList<>();
        for (Professor professor : professorList) {
            Optional<ProfessorResponseDto> professorResponseDto = mapstructMapper.professorEntityToProfessorResponseDto(professor);
            responseDtoList.add(professorResponseDto.orElse(null));
        }
        professorListResponseDto.setProfessorResponseDtoList(responseDtoList);
        return professorListResponseDto;
    }


}
