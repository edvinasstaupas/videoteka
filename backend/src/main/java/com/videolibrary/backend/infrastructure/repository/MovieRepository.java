package com.videolibrary.backend.infrastructure.repository;

import com.videolibrary.backend.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer>, JpaRepository<Movie, Integer> {
}
