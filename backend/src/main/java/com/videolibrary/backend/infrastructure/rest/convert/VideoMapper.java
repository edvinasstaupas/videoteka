package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.domain.entity.Video;
import com.videolibrary.backend.infrastructure.rest.dto.CreateGenreDto;
import com.videolibrary.backend.infrastructure.rest.dto.CreateVideoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface VideoMapper {

    @Mapping(target = "id", ignore = true)
    Video map(CreateVideoDto video);

    @Mapping(target = "id", ignore = true)
    Genre map(CreateGenreDto genre);
}
