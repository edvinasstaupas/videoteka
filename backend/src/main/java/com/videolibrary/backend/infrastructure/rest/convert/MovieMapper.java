package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Movie;
import com.videolibrary.backend.infrastructure.rest.dto.MovieDto;
import com.videolibrary.backend.infrastructure.rest.dto.VideoPreview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface MovieMapper extends IMapper<Movie, MovieDto> {

     @Override
//     @Mapping(source = "video.thumbnailUrl", target = "thumbnailUrl")
     MovieDto map(Movie entity);

}
