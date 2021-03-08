package com.alisonjs.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DatasetDatesDto {

	@JsonProperty("start_date")
	Date startDate;

	@JsonProperty("end_date")
	Date endDate;

}
