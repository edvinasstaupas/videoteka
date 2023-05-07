package com.videolibrary.backend.dto;

import lombok.Data;

@Data
public class VideoPreview {
    private Integer id;
    private String title;
    private String thumbnailPathId;
}
