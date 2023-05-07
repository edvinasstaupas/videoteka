package com.videolibrary.backend.infrastructure.rest.controller;
import com.videolibrary.backend.domain.service.SeriesService;
import com.videolibrary.backend.infrastructure.rest.convert.SeriesMapper;
import com.videolibrary.backend.infrastructure.rest.dto.SeriesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("series")
@RestController
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;

    private final SeriesMapper seriesMapper;

    @GetMapping
    public Page<SeriesDto> getSeries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest request = PageRequest.of(page, size);
        return seriesService.getSeriesOrderedByEpisodeReleaseDate(request).map(seriesMapper::map);
    }

}
