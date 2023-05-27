package com.videolibrary.backend.infrastructure.rest.dto;


import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SeriesDto {
    private Integer version;
    private Integer id;
    private List<SeasonDto> seasons;
    private Set<GenreDto> genres;
    private String title;
    private String description;
    private String thumbnailId;
}