package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.infrastructure.sql.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Page<Genre> getGenres(PageRequest request) {
        return genreRepository.findAll(request);
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }
}
