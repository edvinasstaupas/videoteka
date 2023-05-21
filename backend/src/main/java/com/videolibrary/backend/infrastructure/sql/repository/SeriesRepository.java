package com.videolibrary.backend.infrastructure.sql.repository;

import com.videolibrary.backend.domain.entity.Series;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends BaseRepository<Series, Integer>, JpaSpecificationExecutor<Series> {
}
