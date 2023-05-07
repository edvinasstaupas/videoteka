package com.videolibrary.backend.infrastructure.rest.dto;


import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.domain.entity.Season;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
public class SeriesDto {

    private Integer id;
    private List<SeasonDto> seasons;
    private Set<GenreDto> genres;
    private String title;
    private String description;
    private String thumbnailUrl;

}