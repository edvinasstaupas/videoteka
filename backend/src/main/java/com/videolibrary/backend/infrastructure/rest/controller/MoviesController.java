package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.entity.Movie;
import com.videolibrary.backend.domain.service.MovieService;
import com.videolibrary.backend.domain.service.SpecificationBuilder;
import com.videolibrary.backend.infrastructure.rest.convert.MovieMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateMovieDto;
import com.videolibrary.backend.infrastructure.rest.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("movies")
@RestController
@RequiredArgsConstructor
public class MoviesController {

    private final MovieService moviesService;

    private final MovieMapper movieMapper;

    @GetMapping
    public Page<MovieDto> getMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Integer> genreIds) {
        PageRequest request = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "releaseDate"));
        Specification<Movie> specification = SpecificationBuilder.searchSpecification(title, genreIds);
        return moviesService.getMovies(request, specification).map(movieMapper::map);
    }

    @PostMapping
    public MovieDto createMovie(@RequestBody CreateMovieDto dto) {
        Movie movie = movieMapper.map(dto);
        Movie entity = moviesService.createMovie(movie, dto.getGenreIds());
        return movieMapper.map(entity);
    }

}
