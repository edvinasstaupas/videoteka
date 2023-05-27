package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Movie;
import com.videolibrary.backend.infrastructure.rest.convert.MovieMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateMovieDto;
import com.videolibrary.backend.infrastructure.sql.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreService genreService;
    private final MovieMapper movieMapper;
    private final VideoService videoService;

    public Page<Movie> getMovies(PageRequest request, Specification<Movie> specification) {
        return movieRepository.findAll(specification, request);
    }

    public Movie createMovie(CreateMovieDto dto) {
        Movie movie = movieMapper.map(dto);
        movie.setVideo(videoService.createVideo(dto.getVideo()));
        movie.setGenres(genreService.findAllById(dto.getGenreIds()));
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Integer id, CreateMovieDto dto) {
        Movie movie = movieMapper.map(dto);
        List<Integer> genreIds = dto.getGenreIds();
        if (genreIds != null)
            movie.setGenres(genreService.findAllById(genreIds));
        Movie existingMovie = movieRepository.findByIdOrThrow(id);
        movieMapper.update(movie, existingMovie);
        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    public Movie getMovie(Integer id) {
        return movieRepository.findByIdOrThrow(id);
    }
}
