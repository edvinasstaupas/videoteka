package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

@Data
public class HistoryDto {
    private Integer videoId;
    private String title;
    private String description;
}
