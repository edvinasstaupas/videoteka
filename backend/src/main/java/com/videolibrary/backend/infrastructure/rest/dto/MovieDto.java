package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class MovieDto {

    private Integer id;
    private Set<GenreDto> genres;
    private VideoDto video;
    private String title;
    private String description;
    private LocalDate releaseDate;

}