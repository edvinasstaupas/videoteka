package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

@Data
public class VideoDto {

    private Integer id;
    private String thumbnailPathId;
    private String pathId;

}
