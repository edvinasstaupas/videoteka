package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeriesDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeriesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class, uses = VideoMapper.class)
public interface SeriesMapper {

    SeriesDto map(Series series);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastEpisodeReleaseDate", ignore = true)
    @Mapping(target = "seasons", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Series map(CreateSeriesDto series);
}
