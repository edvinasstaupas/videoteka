package com.videolibrary.backend.infrastructure.sql.repository;

import com.videolibrary.backend.domain.exception.DomainEntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {

    default T findByIdOrThrow(Integer id) {
        return findById(id).orElseThrow(() -> new DomainEntityNotFoundException("Entity with id " + id + " not found"));
    }
}
