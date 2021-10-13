package com.alisonjs.business.service;

import com.alisonjs.business.domain.Dataset;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DatasetService {

	/**
	 * Create or update a dataset
	 * @param dataset Dataset
	 * @return created or updated dataset
	 */
	Dataset save(Dataset dataset);

	List<String> getCountries();

	Dataset getCountryInfo(String country, Date date);

	List<Dataset> getDailyVaccinations(String country, Date date);

	List<Dataset> getTotalVaccinations(Date date);

	List<Dataset> getTotalVaccinations(Integer limit, Date date);

	List<LocalDate> getDates(String country);

}
