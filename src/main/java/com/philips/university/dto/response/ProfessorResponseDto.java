package com.philips.university.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProfessorResponseDto {

    @JsonProperty(required = true)
    @NotNull
    private Long id;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String name;

    @NotNull
    private DepartmentResponseDto departmentResponseDto;

}
