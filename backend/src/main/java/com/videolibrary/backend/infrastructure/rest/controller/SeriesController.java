package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.domain.service.SeasonService;
import com.videolibrary.backend.domain.service.SeriesService;
import com.videolibrary.backend.domain.service.SpecificationBuilder;
import com.videolibrary.backend.infrastructure.rest.convert.SeasonMapper;
import com.videolibrary.backend.infrastructure.rest.convert.SeriesMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeasonDto;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeriesDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeasonDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeriesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("series")
@RestController
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;
    private final SeasonService seasonService;
    private final SeriesMapper seriesMapper;
    private final SeasonMapper seasonMapper;

    @GetMapping
    public Page<SeriesDto> getSeries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Integer> genreIds) {
        PageRequest request = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "seasons.episodes.releaseDate"));
        Specification<Series> specification = SpecificationBuilder.searchSpecification(title, genreIds);
        return seriesService.getSeries(request, specification).map(seriesMapper::map);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public SeriesDto createSeries(@RequestBody CreateSeriesDto dto) {
        Series entity = seriesService.createSeries(dto);
        return seriesMapper.map(entity);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public SeriesDto updateSeries(@PathVariable Integer id, @RequestBody CreateSeriesDto dto,
            @RequestParam(required = false) Integer version) {
        Series entity = seriesService.updateSeries(id, dto, version);
        return seriesMapper.map(entity);
    }

    @PostMapping("/{seriesId}/seasons")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public SeasonDto createSeason(@PathVariable Integer seriesId, @RequestBody CreateSeasonDto dto) {
        Season entity = seasonService.createSeason(seriesId, dto);
        return seasonMapper.map(entity);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public void deleteSeries(@PathVariable Integer id) {
        seriesService.deleteSeries(id);
    }
}
