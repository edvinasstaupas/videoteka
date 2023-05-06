package com.videolibrary.backend.convert;

import com.videolibrary.backend.dto.VideoPreview;
import com.videolibrary.backend.dto.VideoType;
import com.videolibrary.backend.entities.Episode;
import com.videolibrary.backend.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface VideoMapper {

    @Mapping(source = ".", target = "type")
    @Mapping(source = "video.thumbnailUrl", target = "thumbnailUrl")
    VideoPreview map(Movie source);

    @Mapping(source = ".", target = "type")
    @Mapping(source = "season.title", target = "title")
    @Mapping(source = "video.thumbnailUrl", target = "thumbnailUrl")
    VideoPreview map(Episode source);

    default VideoType mapType(Movie source) {
        return VideoType.MOVIE;
    }

    default VideoType mapType(Episode source) {
        return VideoType.SERIES;
    }
}
