package com.videolibrary.backend.repository;

import com.videolibrary.backend.entities.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EpisodeRepository extends PagingAndSortingRepository<Episode, Integer>, JpaRepository<Episode, Integer> {
}
