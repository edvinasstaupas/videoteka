package com.videolibrary.backend.infrastructure.repository;

import com.videolibrary.backend.domain.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SeriesRepository extends PagingAndSortingRepository<Series, Integer>, JpaRepository<Series, Integer> {

    @Query("SELECT DISTINCT s FROM Series s JOIN s.seasons se JOIN se.episodes e ORDER BY e.releaseDate ASC")
    Page<Series> getSeriesByEpisodeReleaseDate(Pageable pageable);

}
