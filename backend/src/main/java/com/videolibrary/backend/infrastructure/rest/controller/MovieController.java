package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.service.MovieService;
import com.videolibrary.backend.infrastructure.rest.convert.MovieMapper;
import com.videolibrary.backend.infrastructure.rest.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("movie")
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService moviesService;

    private final MovieMapper movieMapper;

    @GetMapping
    public List<MovieDto> getMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "releaseDate") String sortBy) {
        PageRequest request = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
        return moviesService.getMovies(request).map(movieMapper::map).toList();
    }

}
