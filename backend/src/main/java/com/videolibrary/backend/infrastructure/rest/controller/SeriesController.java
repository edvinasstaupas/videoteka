package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.domain.service.SeriesService;
import com.videolibrary.backend.domain.service.SpecificationBuilder;
import com.videolibrary.backend.infrastructure.rest.convert.SeriesMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeriesDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeriesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final SeriesMapper seriesMapper;

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
        Series series = seriesMapper.map(dto);
        Series entity = seriesService.createSeries(series);
        return seriesMapper.map(entity);
    }

}
