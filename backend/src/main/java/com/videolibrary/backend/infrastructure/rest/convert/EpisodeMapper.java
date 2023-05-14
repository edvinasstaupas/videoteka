package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.infrastructure.rest.dto.CreateEpisodeDto;
import com.videolibrary.backend.infrastructure.rest.dto.EpisodeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = DefaultMapperConfig.class, uses = VideoMapper.class)
public interface EpisodeMapper {

    List<Episode> mapToEntities(List<CreateEpisodeDto> episodes);

    List<EpisodeDto> mapToDtos(List<Episode> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "season", ignore = true)
    @Mapping(target = "numberInSeason", ignore = true)
    Episode map(CreateEpisodeDto episode);

    EpisodeDto map(Episode episode);
}
