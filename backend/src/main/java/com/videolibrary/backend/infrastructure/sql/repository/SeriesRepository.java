package com.videolibrary.backend.infrastructure.sql.repository;

import com.videolibrary.backend.domain.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SeriesRepository extends PagingAndSortingRepository<Series, Integer>, JpaRepository<Series, Integer> {
}
