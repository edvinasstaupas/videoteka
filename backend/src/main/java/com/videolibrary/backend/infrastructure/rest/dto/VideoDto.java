package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.*;

@Data
public class VideoDto {

    private Integer id;
    private String thumbnailUrl;
    private String url;

}
