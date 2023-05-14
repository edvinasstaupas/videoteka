package com.videolibrary.backend.infrastructure.rest.dto;

import lombok.Data;

@Data
public class CreateVideoDto {

    private String thumbnailPathId;
    private String pathId;
}
