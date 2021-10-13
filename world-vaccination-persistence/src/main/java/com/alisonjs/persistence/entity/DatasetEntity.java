package com.alisonjs.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "dataset")
public class DatasetEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String country;

	@Column(name = "iso_code")
	private String isoCode;

	@Column(columnDefinition = "DATE")
	private LocalDate date;

	@Column(name = "total_vaccinations")
	private Long totalVaccinations;

	@Column(name = "people_vaccinated")
	private Long peopleVaccinated;

	@Column(name = "people_fully_vaccinated")
	private Long peopleFullyVaccinated;

	@Column(name = "daily_vaccinations_raw")
	private Integer dailyVaccinationsRaw;

	@Column(name = "daily_vaccinations")
	private Integer dailyVaccinations;

	@Column(name = "total_vaccinations_per_hundred")
	private Double totalVaccinationsPerHundred;

	@Column(name = "people_vaccinated_per_hundred")
	private Double peopleVaccinatedPerHundred;

	@Column(name = "people_fully_vaccinated_per_hundred")
	private Double peopleFullyVaccinatedPerHundred;

	@Column(name = "daily_vaccinations_per_million")
	private Double dailyVaccinationsPerMillion;

	private String vaccines;

	@Column(name = "source_name")
	private String sourceName;

	@Column(name = "source_website")
	private String sourceWebsite;

}
