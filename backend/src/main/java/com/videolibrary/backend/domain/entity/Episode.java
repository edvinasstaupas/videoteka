package com.videolibrary.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Episode extends HistoryAware {

    @ManyToOne
    private Season season;

    private LocalDate releaseDate;
}
