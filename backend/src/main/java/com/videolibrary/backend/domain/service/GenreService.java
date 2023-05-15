package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.infrastructure.rest.convert.GenreMapper;
import com.videolibrary.backend.infrastructure.sql.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public Page<Genre> getGenres(PageRequest request) {
        return genreRepository.findAll(request);
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteGenre(Integer id) {
        genreRepository.deleteById(id);
    }

    public Genre updateGenre(Integer id, Genre genre) {
        Genre existingGenre = genreRepository.findByIdOrThrow(id);
        genreMapper.update(genre, existingGenre);
        return genreRepository.save(existingGenre);
    }
}
