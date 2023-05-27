package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEpisodeDto {

    private CreateVideoDto video;

    private String title;

    private String description;

    private LocalDate releaseDate;

}
