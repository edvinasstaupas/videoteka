package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.domain.service.GenreService;
import com.videolibrary.backend.infrastructure.rest.convert.GenreMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateGenreDto;
import com.videolibrary.backend.infrastructure.rest.dto.GenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("genres")
@RestController
@RequiredArgsConstructor
public class GenresController {
    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @GetMapping
    public Page<GenreDto> getGenres(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest request = PageRequest.of(page, size, Sort.by("name"));
        return genreService.getGenres(request).map(genreMapper::map);
    }

    @PostMapping
    public GenreDto createGenre(@RequestBody CreateGenreDto dto) {
        Genre genre = genreMapper.map(dto);
        Genre entity = genreService.createGenre(genre);
        return genreMapper.map(entity);
    }
}