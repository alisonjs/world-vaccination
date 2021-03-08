package com.alisonjs.business.service.impl;

import com.alisonjs.business.domain.Dataset;
import com.alisonjs.business.repository.DatasetRepository;
import com.alisonjs.business.service.DatasetService;

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
        return repository.findCountryInfo(country, date);
    }

    @Override
    public List<Dataset> getDailyVaccinations(String country, Date date) {
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
    public List<Date> getDates(String country) {
        return repository.getDatesByCountry(country);
    }
}
