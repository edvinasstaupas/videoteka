package com.videolibrary.backend.infrastructure.rest.dto;

import com.videolibrary.backend.domain.entity.HistoryAware;
import lombok.Data;

@Data
public class HistoryDto {
    private VideoDto video;
    private String title;
    private String description;
    private HistoryAware.Type type;
    private Integer targetId;
}
