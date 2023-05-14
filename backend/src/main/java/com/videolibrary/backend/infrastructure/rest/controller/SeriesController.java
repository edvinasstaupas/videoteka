package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.domain.service.EpisodeService;
import com.videolibrary.backend.domain.service.SeasonService;
import com.videolibrary.backend.domain.service.SeriesService;
import com.videolibrary.backend.domain.service.SpecificationBuilder;
import com.videolibrary.backend.infrastructure.rest.convert.EpisodeMapper;
import com.videolibrary.backend.infrastructure.rest.convert.SeasonMapper;
import com.videolibrary.backend.infrastructure.rest.convert.SeriesMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateEpisodeDto;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeasonDto;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeriesDto;
import com.videolibrary.backend.infrastructure.rest.dto.EpisodeDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeasonDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeriesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RequestMapping("series")
@RestController
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;
    private final SeasonService seasonService;
    private final EpisodeService episodeService;
    private final SeriesMapper seriesMapper;
    private final SeasonMapper seasonMapper;
    private final EpisodeMapper episodeMapper;

    @GetMapping
    public Page<SeriesDto> getSeries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Integer> genreIds) {
        PageRequest request = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "lastEpisodeReleaseDate"));
        Specification<Series> specification = SpecificationBuilder.searchSpecification(title, genreIds);
        return seriesService.getSeries(request, specification).map(seriesMapper::map);
    }

    @PostMapping
    public SeriesDto createSeries(@RequestBody CreateSeriesDto dto) {
        Series entity = seriesService.createSeries(dto);
        return seriesMapper.map(entity);
    }

    @PostMapping("/{seriesId}/seasons")
    public SeasonDto createSeason(@PathVariable Integer seriesId, @RequestBody CreateSeasonDto dto) {
        Season season = seasonService.createSeason(seriesId, dto);
        return seasonMapper.map(season);
    }

    @PostMapping("/{seriesId}/seasons/{seasonId}/episodes")
    public List<EpisodeDto> createEpisodes(@PathVariable Integer seriesId, @PathVariable Integer seasonId, @RequestBody List<CreateEpisodeDto> dto) {
        List<Episode> episodes = episodeMapper.mapToEntities(dto);
        List<Episode> entities = episodeService.createEpisodes(seriesId, seasonId, episodes);
        return episodeMapper.mapToDtos(entities);
    }

}
