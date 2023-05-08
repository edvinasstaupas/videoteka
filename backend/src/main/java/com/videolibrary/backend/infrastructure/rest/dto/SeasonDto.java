package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeasonDto {

    private Integer id;
    private String title;
    private String description;
    private List<EpisodeDto> episodes;

}
