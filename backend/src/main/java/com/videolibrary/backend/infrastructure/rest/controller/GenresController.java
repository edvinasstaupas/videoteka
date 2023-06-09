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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Page<GenreDto> searchGenres(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name
    ) {
        PageRequest request = PageRequest.of(page, size, Sort.by("name"));
        return genreService.getGenres(name, request).map(genreMapper::map);
    }

    @GetMapping("{id}")
    public GenreDto getGenre(@PathVariable Integer id) {
        Genre genre = genreService.getGenre(id);
        return genreMapper.map(genre);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public GenreDto createGenre(@RequestBody CreateGenreDto dto) {
        Genre entity = genreService.createGenre(dto);
        return genreMapper.map(entity);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public void deleteGenre(@PathVariable Integer id) {
        genreService.deleteGenre(id);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public GenreDto updateGenre(@PathVariable Integer id, @RequestBody CreateGenreDto dto) {
        Genre entity = genreService.updateGenre(id, dto);
        return genreMapper.map(entity);
    }
}
