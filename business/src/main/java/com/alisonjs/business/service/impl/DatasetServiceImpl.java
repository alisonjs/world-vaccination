package com.alisonjs.business.service.impl;

import com.alisonjs.business.domain.Dataset;
import com.alisonjs.business.repository.DatasetRepository;
import com.alisonjs.business.service.DatasetService;

public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository repository;

    public DatasetServiceImpl(DatasetRepository repository) {
        this.repository = repository;
    }


    @Override
    public Dataset save(Dataset dataset) {
        return repository.save(dataset);
    }
}
