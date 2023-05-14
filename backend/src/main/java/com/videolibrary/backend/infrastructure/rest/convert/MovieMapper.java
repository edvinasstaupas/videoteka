package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Movie;
import com.videolibrary.backend.infrastructure.rest.dto.CreateMovieDto;
import com.videolibrary.backend.infrastructure.rest.dto.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class, uses = VideoMapper.class)
public interface MovieMapper {

    MovieDto map(Movie entity);

    @Mapping(target = "id", ignore = true)
    Movie map(CreateMovieDto dto);

}
