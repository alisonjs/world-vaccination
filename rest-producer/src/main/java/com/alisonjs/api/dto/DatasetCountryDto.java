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
public class DatasetCountryDto implements Serializable {

    private String country;

    @JsonProperty("daily_vaccinations")
    private Integer dailyVaccinations;

    private Date date;

    @JsonProperty("total_vaccinations")
    private Long totalVaccinations;

    @JsonProperty("total_vaccinations_per_hundred")
    private Double totalVaccinationsPerHundred;

}
