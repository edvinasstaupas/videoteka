package com.videolibrary.backend.infrastructure.sql.repository;

import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.domain.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeriesRepository extends PagingAndSortingRepository<Series, Integer>, JpaRepository<Series, Integer> {

    @Query(value = "SELECT DISTINCT s " +
            "FROM Series s " +
            "JOIN s.genres g " +
            "JOIN s.seasons se " +
            "JOIN se.episodes e " +
            "WHERE s.title LIKE :title " +
            "AND g.id IN :genreIds " +
            "ORDER BY e.releaseDate DESC")
    Page<Series> getSeriesFilterGenreIds(Pageable pageable, @Param("title") String title, @Param("genreIds") List<Integer> genreIds);

    @Query(value = "SELECT DISTINCT s " +
            "FROM Series s " +
            "JOIN s.genres g " +
            "JOIN s.seasons se " +
            "JOIN se.episodes e " +
            "WHERE s.title LIKE :title " +
            "ORDER BY e.releaseDate DESC")
    Page<Series> getSeries(Pageable pageable, @Param("title") String title);

}
