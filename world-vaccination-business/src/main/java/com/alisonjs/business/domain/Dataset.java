package com.alisonjs.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Dataset {

	private long version;

	private Long id;

	private String country;

	private String isoCode;

	private LocalDate date;

	private Long totalVaccinations;

	private Long peopleVaccinated;

	private Long peopleFullyVaccinated;

	private Integer dailyVaccinationsRaw;

	private Integer dailyVaccinations;

	private Double totalVaccinationsPerHundred;

	private Double peopleVaccinatedPerHundred;

	private Double peopleFullyVaccinatedPerHundred;

	private Double dailyVaccinationsPerMillion;

	private String vaccines;

	private String sourceName;

	private String sourceWebsite;

}
