package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.domain.entity.Movie;
import com.videolibrary.backend.infrastructure.sql.repository.GenreRepository;
import com.videolibrary.backend.infrastructure.sql.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public Page<Movie> getMovies(PageRequest request, Specification<Movie> specification) {
        return movieRepository.findAll(specification, request);
    }

    public Movie createMovie(Movie movie, List<Integer> genreIds) {
        List<Genre> genres = genreRepository.findAllById(genreIds);
        movie.setGenres(new HashSet<>(genres));
        return movieRepository.save(movie);
    }
}
