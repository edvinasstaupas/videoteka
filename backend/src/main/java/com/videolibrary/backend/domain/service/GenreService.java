package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.infrastructure.sql.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Set<Integer> findAllIds() {
        return genreRepository.findAllIds();
    }
}
