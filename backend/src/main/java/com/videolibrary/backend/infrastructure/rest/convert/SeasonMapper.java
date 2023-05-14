package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeasonDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeasonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class, uses = EpisodeMapper.class)
public interface SeasonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "series", ignore = true)
    @Mapping(target = "episodes", ignore = true)
    Season map(CreateSeasonDto dto);

    SeasonDto map(Season season);
}
