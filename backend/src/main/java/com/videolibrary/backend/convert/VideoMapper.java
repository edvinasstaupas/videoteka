package com.videolibrary.backend.convert;

import com.videolibrary.backend.dto.VideoPreview;
import com.videolibrary.backend.entities.Episode;
import com.videolibrary.backend.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface VideoMapper {

    @Mapping(source = "video.thumbnailUrl", target = "thumbnailUrl")
    VideoPreview map(Movie source);

    @Mapping(source = "season.title", target = "title")
    @Mapping(source = "video.thumbnailUrl", target = "thumbnailUrl")
    VideoPreview map(Episode source);
}
