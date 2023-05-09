package com.videolibrary.backend.infrastructure.sql.repository;

import com.videolibrary.backend.domain.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer> {

    Page<Series> findAllByTitleContainingAndGenresIn(Pageable pageable, String title, Set<Integer> genreIds);

}