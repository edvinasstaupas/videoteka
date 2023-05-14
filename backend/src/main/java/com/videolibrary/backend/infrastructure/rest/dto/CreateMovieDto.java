package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CreateMovieDto {

    private Set<CreateGenreDto> genres;
    private CreateVideoDto video;
    private String title;
    private String description;
    private LocalDate releaseDate;

}