package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateSeriesDto {

    private List<Integer> genreIds = List.of();
    private String title;
    private String description;
    private String thumbnailPathId;
}
