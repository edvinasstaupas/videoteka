package com.videolibrary.backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.videolibrary.backend.domain.entity.HistoryAware.Type.EPISODE;

@Entity
@Getter
@Setter
public class Episode extends HistoryAware {

    @ManyToOne
    private Season season;

    private LocalDate releaseDate;

    @Override
    public HistoryAware.Type getType() {
        return EPISODE;
    }
}
