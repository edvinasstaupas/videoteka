package com.videolibrary.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Video video;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    private Integer season;

    private Integer episodeInSeason;

}
