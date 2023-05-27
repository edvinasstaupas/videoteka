package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateSeriesDto {

    private List<Integer> genreIds;

    private String title;

    private String description;

    private UUID thumbnailId;

}
