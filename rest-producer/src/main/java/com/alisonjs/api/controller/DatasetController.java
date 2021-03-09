package com.alisonjs.api.controller;

import com.alisonjs.api.dto.DatasetCountryDto;
import com.alisonjs.api.dto.DatasetDailyVaccinationsDto;
import com.alisonjs.api.dto.DatasetDatesDto;
import com.alisonjs.api.dto.DatasetTotalVaccinationsDto;
import com.alisonjs.api.dto.mapper.DatasetDtoMapper;
import com.alisonjs.business.service.DatasetService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/dataset", produces="application/json")
@CrossOrigin(origins = "*")
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

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List all countries")
	})
	@GetMapping("/countries")
	public ResponseEntity<List<String>> countries() {
		return ResponseEntity.ok(datasetService.getCountries());
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List all countries with total vaccinations"),
			@ApiResponse(code = 403, message = "Forbidden"),
	})
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

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get country information"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Dataset not found with the given name or date")
	})
	@GetMapping("/country")
	public ResponseEntity<DatasetCountryDto> country(@RequestParam("country") String country,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return ResponseEntity.ok(mapper.toCountryDto(datasetService.getCountryInfo(country, date)));
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get country daily vaccinations list"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Dataset not found with the given name or date")
	})
	@GetMapping("/country/daily_vaccinations")
	public ResponseEntity<List<DatasetDailyVaccinationsDto>> dailyVaccinations(@RequestParam("country") String country,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		List<DatasetDailyVaccinationsDto> results = new ArrayList<>();
		datasetService.getDailyVaccinations(country, date)
				.forEach(item -> results.add(mapper.toDailyVaccinationsDto(item)));
		return ResponseEntity.ok(results);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get country start and end information's date"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Dataset not found with the given country")
	})
	@GetMapping("/country/dates")
	public ResponseEntity<DatasetDatesDto> dailyVaccinations(@RequestParam("country") String country) {
		return ResponseEntity.ok(mapper.toCountryDates(datasetService.getDates(country)));
	}

}
