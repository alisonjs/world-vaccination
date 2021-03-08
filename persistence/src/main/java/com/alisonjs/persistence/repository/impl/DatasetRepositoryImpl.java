package com.alisonjs.persistence.repository.impl;

import com.alisonjs.business.domain.Dataset;
import com.alisonjs.business.repository.DatasetRepository;
import com.alisonjs.persistence.entity.DatasetEntity;
import com.alisonjs.persistence.mapper.DatasetEntityMapper;
import com.alisonjs.persistence.repository.DatasetJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DatasetRepositoryImpl implements DatasetRepository {

	private final DatasetJpaRepository repository;

	private final DatasetEntityMapper mapper;

	public DatasetRepositoryImpl(DatasetJpaRepository repository, DatasetEntityMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Dataset save(Dataset dataset) {
		return mapper.toModel(repository.save(mapper.fromModel(dataset)));
	}

	@Override
	public List<String> getCountries() {
		return repository.findAllCountries();
	}

	@Override
	public Dataset findCountryInfo(String country, Date date) {
		return repository.findDatasetEntityByCountryIgnoreCaseAndDate(country, date).map(mapper::toModel).orElse(null);
	}

	@Override
	public List<Dataset> findCountryInfos(String country, Date date) {
		List<Dataset> results = new ArrayList<>();
		repository.findAllByCountryIgnoreCaseAndDateIsLessThanEqualOrderByDateAsc(country, date)
				.forEach(item -> results.add(mapper.toModel(item)));
		return results;
	}

	@Override
	public List<Dataset> findAllByDate(Date date) {
		List<Dataset> results = new ArrayList<>();
		repository.findAllByTotalVaccinationsNotNullAndDate(date).forEach(item -> results.add(mapper.toModel(item)));
		return results;
	}

	@Override
	public List<Dataset> findAllByDateAndLimit(Date date, Integer limit) {
		List<Dataset> results = new ArrayList<>();
		repository
				.findAllByTotalVaccinationsIsNotNullAndDate(date,
						PageRequest.of(0, limit, Sort.by("totalVaccinations").descending()))
				.forEach(item -> results.add(mapper.toModel(item)));
		return results;
	}

	@Override
	public List<Date> getDatesByCountry(String country) {

		DatasetEntity startDate = repository.findFirstByCountryIgnoreCaseOrderByDateAsc(country);

		DatasetEntity endDate = repository.findFirstByCountryIgnoreCaseOrderByDateDesc(country);

		return List.of(startDate.getDate(), endDate.getDate());
	}

}
