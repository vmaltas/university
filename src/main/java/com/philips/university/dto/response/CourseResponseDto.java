package com.philips.university.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CourseResponseDto {

    @JsonProperty(required = true)
    @NotNull
    private Long id;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String name;


    @JsonProperty(required = true)
    @NotBlank
    private Integer credit;

    @NotNull
    private DepartmentResponseDto departmentResponseDto;

}
