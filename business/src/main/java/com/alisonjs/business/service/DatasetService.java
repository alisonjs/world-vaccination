package com.alisonjs.business.service;

import com.alisonjs.business.domain.Dataset;

public interface DatasetService {

    /**
     * Create or update a dataset
     * @param dataset Dataset
     * @return created or updated dataset
     */
    Dataset save(Dataset dataset);

}
