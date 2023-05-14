package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.infrastructure.rest.dto.CreateGenreDto;
import com.videolibrary.backend.infrastructure.rest.dto.GenreDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface GenreMapper {

    GenreDto map(Genre genre);

    @Mapping(target = "id", ignore = true)
    Genre map(CreateGenreDto dto);
}
