package service.impl;

import domain.Dataset;
import repository.DatasetRepository;
import service.DatasetService;

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
