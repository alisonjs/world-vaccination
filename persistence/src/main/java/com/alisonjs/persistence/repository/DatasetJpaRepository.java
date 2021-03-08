package com.alisonjs.persistence.repository;

import com.alisonjs.persistence.entity.DatasetEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DatasetJpaRepository extends JpaRepository<DatasetEntity, Long> {

    @Query("SELECT DISTINCT d.country FROM dataset d order by d.country")
    List<String> findAllCountries();

    Optional<DatasetEntity> findDatasetEntityByCountryIgnoreCaseAndDate(String country, Date date);

    List<DatasetEntity> findAllByCountryIgnoreCaseAndDateIsLessThanEqualOrderByDateAsc(String country, Date date);

    List<DatasetEntity> findAllByTotalVaccinationsNotNullAndDate(Date date);

    List<DatasetEntity> findAllByTotalVaccinationsIsNotNullAndDate(Date date, Pageable pageable);

    DatasetEntity findFirstByCountryIgnoreCaseOrderByDateAsc(String country);

    DatasetEntity findFirstByCountryIgnoreCaseOrderByDateDesc(String country);

}
