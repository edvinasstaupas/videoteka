package com.videolibrary.backend.repository;

import com.videolibrary.backend.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SeriesRepository extends PagingAndSortingRepository<Series, Integer>, JpaRepository<Series, Integer> {
}
