package com.philips.university.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDto {

    @JsonProperty(required = true)
    @NotNull
    private Long courseId;

    @JsonProperty(required = true)
    @NotNull
    private Long professorId;

    @JsonProperty(required = true)
    @NotNull
    private Integer semester;

    @JsonProperty(required = true)
    @NotNull
    private Integer year;


}
