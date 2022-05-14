package com.philips.university.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ScheduleResponseDto {

    @JsonProperty(required = true)
    @NotNull
    private Long id;

    @JsonProperty(required = true)
    @NotBlank
    private Integer semester;

    @JsonProperty(required = true)
    @NotBlank
    private Integer year;

    @NotNull
    private ProfessorResponseDto professorResponseDto;

    @NotNull
    private CourseResponseDto courseResponseDto;

}
