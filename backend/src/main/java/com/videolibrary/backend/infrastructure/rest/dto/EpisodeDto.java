package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EpisodeDto {

    private Integer id;
    private VideoDto video;
    private String title;
    private String description;
    private LocalDate releaseDate;
}
