package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EpisodeDto {

    private Integer id;
    private VideoDto video;
    private SeasonDto season;
    private String name;
    private String description;
    private Integer numberInSeason;
    private LocalDate releaseDate;
}
