package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CreateSeriesDto {

    private List<CreateSeasonDto> seasons = List.of();
    private Set<CreateGenreDto> genres;
    private String title;
    private String description;
    private String thumbnailPathId;
}
