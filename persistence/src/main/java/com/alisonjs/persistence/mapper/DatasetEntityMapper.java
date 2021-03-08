package com.alisonjs.persistence.mapper;

import com.alisonjs.business.domain.Dataset;
import com.alisonjs.persistence.entity.DatasetEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DatasetEntityMapper {

    DatasetEntity fromModel(Dataset dataset);

    Dataset toModel(DatasetEntity datasetEntity);

}
