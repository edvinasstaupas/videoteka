package com.videolibrary.backend.infrastructure.sql.repository;

import com.videolibrary.backend.domain.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface HistoryRepository extends BaseRepository<History, Integer>, JpaSpecificationExecutor<History> {

    @Query("SELECT h FROM History h " +
            "JOIN h.historyAware ha " +
            "JOIN h.user u WHERE u.id = :userId " +
            "GROUP BY ha.id " +
            "ORDER BY MAX(h.timestamp) DESC")
    Page<History> findHistoryForUser(String userId, Pageable pageable);
}
