package com.alisonjs.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DatasetTotalVaccinationsDto implements Serializable {
    private String country;

    private Date date;

    @JsonProperty("total_vaccinations")
    private Long totalVaccinations;
}
