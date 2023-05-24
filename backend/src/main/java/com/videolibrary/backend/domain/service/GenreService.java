package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.infrastructure.rest.convert.GenreMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateGenreDto;
import com.videolibrary.backend.infrastructure.sql.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public Page<Genre> getGenres(String name, PageRequest request) {
        return name != null ?
            genreRepository.findAllByNameStartingWith(name, request) :
            genreRepository.findAll(request);
    }

    public Genre createGenre(CreateGenreDto dto) {
        Genre genre = genreMapper.map(dto);
        return genreRepository.save(genre);
    }

    public Set<Genre> findAllById(Iterable<Integer> ids) {
        return new HashSet<>(genreRepository.findAllById(ids));
    }

    public void deleteGenre(Integer id) {
        genreRepository.deleteById(id);
    }

    public Genre updateGenre(Integer id, CreateGenreDto dto) {
        Genre genre = genreMapper.map(dto);
        Genre existingGenre = genreRepository.findByIdOrThrow(id);
        genreMapper.update(genre, existingGenre);
        return genreRepository.save(existingGenre);
    }
}
