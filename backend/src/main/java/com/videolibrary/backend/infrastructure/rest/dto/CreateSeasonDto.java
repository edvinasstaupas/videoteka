package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateSeasonDto {

    private String title;
    private String description;
    private List<CreateEpisodeDto> episodes = List.of();
}
