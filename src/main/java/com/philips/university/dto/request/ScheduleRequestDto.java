package com.philips.university.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ScheduleRequestDto {

    @JsonProperty(required = true)
    @NotBlank
    private Integer semester;

    @JsonProperty(required = true)
    @NotBlank
    private Integer year;
}
