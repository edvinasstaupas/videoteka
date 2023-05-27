package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeriesDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeriesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = DefaultMapperConfig.class, uses = EpisodeMapper.class)
public interface SeriesMapper {

    @Mapping(source = "thumbnail.id", target = "thumbnailId")
    SeriesDto map(Series series);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "seasons", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "thumbnail", ignore = true)
    @Mapping(target = "id", ignore = true)
    Series map(CreateSeriesDto series);

    void update(Series source, @MappingTarget Series destination);
}
