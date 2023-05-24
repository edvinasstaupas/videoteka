package com.videolibrary.backend.infrastructure.sql.repository;

import com.videolibrary.backend.domain.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreRepository extends BaseRepository<Genre, Integer> {

    Page<Genre> findAllByNameStartingWith(String name, Pageable request);
}
