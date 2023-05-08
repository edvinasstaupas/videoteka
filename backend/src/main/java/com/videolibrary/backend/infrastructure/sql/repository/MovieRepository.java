package com.videolibrary.backend.infrastructure.sql.repository;

import com.videolibrary.backend.domain.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer>, JpaRepository<Movie, Integer> {
    Page<Movie> findAllByTitleLikeAndGenresIn(Pageable pageable, String title, List<Integer> genreIds);
    Page<Movie> findAllByTitleLike(Pageable pageable, String title);

}
