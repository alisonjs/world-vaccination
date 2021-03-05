package com.alisonjs.persistence.repository;

import com.alisonjs.persistence.entity.DatasetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetJpaRepository extends JpaRepository<DatasetEntity, Long> {
}
