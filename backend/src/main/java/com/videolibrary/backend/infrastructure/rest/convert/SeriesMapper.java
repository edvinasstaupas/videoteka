package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.dto.SeriesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface SeriesMapper {

    @Mapping(target = "episode.season", ignore = true)
    SeriesDto map(Series series);

}
