package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.dto.SearchDto;
import com.videolibrary.backend.domain.service.MovieService;
import com.videolibrary.backend.infrastructure.rest.convert.MovieMapper;
import com.videolibrary.backend.infrastructure.rest.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("movie")
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService moviesService;

    private final MovieMapper movieMapper;

    @GetMapping
    public Page<MovieDto> getMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Integer> genreIds) {
        var searchDto = new SearchDto(title, genreIds);
        PageRequest request = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "releaseDate"));
        return moviesService.getMovies(request, searchDto).map(movieMapper::map);
    }

}
