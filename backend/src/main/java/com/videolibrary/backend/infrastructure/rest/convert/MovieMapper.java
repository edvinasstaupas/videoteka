package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Movie;
import com.videolibrary.backend.infrastructure.rest.dto.MovieDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface MovieMapper {

    MovieDto map(Movie entity);

}
