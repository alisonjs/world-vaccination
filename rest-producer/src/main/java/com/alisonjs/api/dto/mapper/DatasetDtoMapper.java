package com.alisonjs.api.dto.mapper;

import com.alisonjs.api.dto.DatasetCountryDto;
import com.alisonjs.api.dto.DatasetDailyVaccinationsDto;
import com.alisonjs.api.dto.DatasetDatesDto;
import com.alisonjs.api.dto.DatasetTotalVaccinationsDto;
import com.alisonjs.business.domain.Dataset;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Mapper
public interface DatasetDtoMapper {

    DatasetCountryDto toCountryDto(Dataset dataset);

    DatasetDailyVaccinationsDto toDailyVaccinationsDto(Dataset dataset);

    DatasetTotalVaccinationsDto toTotalVaccinationsDto(Dataset dataset);

    default DatasetDatesDto toCountryDates(List<Date> dates){
        Iterator<Date> it = dates.iterator();
        return DatasetDatesDto.builder()
                .startDate(it.next())
                .endDate(it.next())
                .build();
    }

}
