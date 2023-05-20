package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class VideoDto {

    private Integer id;
    private UUID thumbnailId;
    private UUID contentId;

}
