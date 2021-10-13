package com.alisonjs.business.service.impl;

import com.alisonjs.business.domain.Dataset;
import com.alisonjs.business.exceptions.NotFoundException;
import com.alisonjs.business.repository.DatasetRepository;
import com.alisonjs.business.service.DatasetService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DatasetServiceImpl implements DatasetService {

	private final DatasetRepository repository;

	public DatasetServiceImpl(DatasetRepository repository) {
		this.repository = repository;
	}

	@Override
	public Dataset save(Dataset dataset) {
		return repository.save(dataset);
	}

	@Override
	public List<String> getCountries() {
		return repository.getCountries();
	}

	@Override
	public Dataset getCountryInfo(String country, Date date) {
		Dataset dataset = repository.findCountryInfo(country, date);
		if(dataset == null){
			throw new NotFoundException("Dataset not found with the given name or date");
		}
		return dataset;
	}

	@Override
	public List<Dataset> getDailyVaccinations(String country, Date date) {
		List<Dataset> dataset_dailies = repository.findCountryInfos(country, date);
		if(dataset_dailies == null || dataset_dailies.isEmpty()){
			throw new NotFoundException("Dataset not found with the given name or date");
		}
		return repository.findCountryInfos(country, date);
	}

	@Override
	public List<Dataset> getTotalVaccinations(Integer limit, Date date) {
		return repository.findAllByDateAndLimit(date, limit);
	}

	@Override
	public List<Dataset> getTotalVaccinations(Date date) {
		return repository.findAllByDate(date);
	}

	@Override
	public List<LocalDate> getDates(String country) {
		List<LocalDate> dates = repository.getDatesByCountry(country);
		if(dates == null || dates.isEmpty()){
			throw new NotFoundException("Dates not found with the given country");
		}
		return repository.getDatesByCountry(country);
	}

}
