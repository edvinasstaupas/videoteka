package com.videolibrary.backend.infrastructure.sql.repository;

import com.videolibrary.backend.domain.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query("SELECT g.id from Genre g")
    Set<Integer> findAllIds();
}
