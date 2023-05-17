package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.infrastructure.rest.dto.CreateEpisodeDto;
import com.videolibrary.backend.infrastructure.rest.dto.EpisodeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = DefaultMapperConfig.class, uses = VideoMapper.class)
public interface EpisodeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "season", ignore = true)
    @Mapping(target = "numberInSeason", ignore = true)
    Episode map(CreateEpisodeDto episode);

    EpisodeDto map(Episode episode);

    void update(Episode source, @MappingTarget Episode target);
}
