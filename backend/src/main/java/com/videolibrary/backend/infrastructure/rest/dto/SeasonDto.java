package com.videolibrary.backend.infrastructure.rest.dto;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.domain.entity.Series;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
public class SeasonDto {

    private Integer id;
    private String title;
    private String description;
    private List<EpisodeDto> episodes;

}
