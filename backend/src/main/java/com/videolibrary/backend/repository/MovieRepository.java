package com.videolibrary.backend.repository;

import com.videolibrary.backend.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer>, JpaRepository<Movie, Integer> {
}
