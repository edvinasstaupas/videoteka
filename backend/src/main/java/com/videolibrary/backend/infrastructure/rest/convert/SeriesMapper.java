package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.domain.entity.Video;
import com.videolibrary.backend.infrastructure.rest.dto.CreateEpisodeDto;
import com.videolibrary.backend.infrastructure.rest.dto.CreateGenreDto;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeasonDto;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeriesDto;
import com.videolibrary.backend.infrastructure.rest.dto.CreateVideoDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeriesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Mapper(config = DefaultMapperConfig.class)
public interface SeriesMapper {

    SeriesDto map(Series series);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = ".", target = "lastEpisodeReleaseDate")
    Series map(CreateSeriesDto series);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "series", ignore = true)
    @Mapping(source = ".", target = "episodes")
    Season map(CreateSeasonDto season);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "season", ignore = true)
    @Mapping(target = "numberInSeason", ignore = true)
    Episode map(CreateEpisodeDto episode);

    //TODO: extract common stuff when doing movies
    @Mapping(target = "id", ignore = true)
    Genre map(CreateGenreDto genre);

    @Mapping(target = "id", ignore = true)
    Video map(CreateVideoDto video);

    default List<Episode> mapEpisodes(CreateSeasonDto dto) {
        return IntStream.range(0, dto.getEpisodes().size())
                .mapToObj(idx -> {
                    Episode episode = map(dto.getEpisodes().get(idx));
                    episode.setNumberInSeason(idx + 1);
                    return episode;
                }).toList();
    }

    default LocalDate mapLastEpisodeReleaseDate(CreateSeriesDto dto) {
        return dto.getSeasons().stream()
                .flatMap(season -> season.getEpisodes().stream())
                .map(CreateEpisodeDto::getReleaseDate)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }
}
