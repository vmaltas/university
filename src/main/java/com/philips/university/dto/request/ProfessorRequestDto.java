package com.philips.university.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequestDto {

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String name;

    @JsonProperty(required = true)
    @NotNull
    private Long departmentId;

}
