package com.alisonjs.business.repository;

import com.alisonjs.business.domain.Dataset;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface DatasetRepository {

	Dataset save(Dataset dataset);

	List<String> getCountries();

	Dataset findCountryInfo(String country, Date date);

	List<Dataset> findCountryInfos(String country, Date date);

	List<Dataset> findAllByDate(Date date);

	List<Dataset> findAllByDateAndLimit(Date date, Integer limit);

	List<LocalDate> getDatesByCountry(String country);

}
