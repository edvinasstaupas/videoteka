package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Video;
import com.videolibrary.backend.infrastructure.rest.dto.VideoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface VideoMapper {


    @Mapping(source = "thumbnail.id", target = "thumbnailId")
    @Mapping(source = "content.id", target = "contentId")
    VideoDto map(Video video);
}
