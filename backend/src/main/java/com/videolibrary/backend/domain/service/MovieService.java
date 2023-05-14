package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Movie;
import com.videolibrary.backend.infrastructure.sql.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Page<Movie> getMovies(PageRequest request, Specification<Movie> specification) {
        return movieRepository.findAll(specification, request);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
