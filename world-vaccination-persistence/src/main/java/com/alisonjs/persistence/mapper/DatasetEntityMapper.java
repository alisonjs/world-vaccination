package com.alisonjs.persistence.mapper;

import com.alisonjs.business.domain.Dataset;
import com.alisonjs.persistence.entity.DatasetEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true))
public interface DatasetEntityMapper {

	DatasetEntity fromModel(Dataset dataset);

	Dataset toModel(DatasetEntity datasetEntity);

}
