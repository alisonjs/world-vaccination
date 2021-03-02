package repository;

import domain.Dataset;

public interface DatasetRepository {
    Dataset save(Dataset dataset);
}
