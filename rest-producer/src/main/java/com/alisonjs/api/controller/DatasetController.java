package com.alisonjs.api.controller;

import com.alisonjs.api.dto.DatasetCountryDto;
import com.alisonjs.api.dto.DatasetDailyVaccinationsDto;
import com.alisonjs.api.dto.DatasetDatesDto;
import com.alisonjs.api.dto.DatasetTotalVaccinationsDto;
import com.alisonjs.api.dto.mapper.DatasetDtoMapper;
import com.alisonjs.business.service.DatasetService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "dataset")
@CrossOrigin(origins = "http://localhost:4200")
public class DatasetController {

	private final DatasetService datasetService;

	private final DatasetDtoMapper mapper;

	public DatasetController(DatasetService datasetService, DatasetDtoMapper mapper) {
		this.datasetService = datasetService;
		this.mapper = mapper;
	}

	@PostMapping()
	public void save() {
	}

	@GetMapping("/countries")
	public ResponseEntity<List<String>> countries() {
		return ResponseEntity.ok(datasetService.getCountries());
	}

	@GetMapping("/countries/total_vaccinations")
	public ResponseEntity<List<DatasetTotalVaccinationsDto>> totalVaccinations(
			@RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		List<DatasetTotalVaccinationsDto> results = new ArrayList<>();
		if (limit != null)
			datasetService.getTotalVaccinations(limit, date)
					.forEach(item -> results.add(mapper.toTotalVaccinationsDto(item)));
		else
			datasetService.getTotalVaccinations(date).forEach(item -> results.add(mapper.toTotalVaccinationsDto(item)));
		return ResponseEntity.ok(results);
	}

	@GetMapping("/country")
	public ResponseEntity<DatasetCountryDto> country(@RequestParam("country") String country,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return ResponseEntity.ok(mapper.toCountryDto(datasetService.getCountryInfo(country, date)));
	}

	@GetMapping("/country/daily_vaccinations")
	public ResponseEntity<List<DatasetDailyVaccinationsDto>> dailyVaccinations(@RequestParam("country") String country,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		List<DatasetDailyVaccinationsDto> results = new ArrayList<>();
		datasetService.getDailyVaccinations(country, date)
				.forEach(item -> results.add(mapper.toDailyVaccinationsDto(item)));
		return ResponseEntity.ok(results);
	}

	@GetMapping("/country/dates")
	public ResponseEntity<DatasetDatesDto> dailyVaccinations(@RequestParam("country") String country) {
		return ResponseEntity.ok(mapper.toCountryDates(datasetService.getDates(country)));
	}

}
