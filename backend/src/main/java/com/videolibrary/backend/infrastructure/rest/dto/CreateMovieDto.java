package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateMovieDto {

    private List<Integer> genreIds = List.of();
    private CreateVideoDto video;
    private String title;
    private String description;
    private LocalDate releaseDate;

}