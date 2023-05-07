package com.videolibrary.backend.infrastructure.rest.controller;
import com.videolibrary.backend.domain.service.SeriesService;
import com.videolibrary.backend.infrastructure.rest.convert.SeriesMapper;
import com.videolibrary.backend.infrastructure.rest.dto.SeriesDto;
import lombok.RequiredArgsConstructor;
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
    public List<SeriesDto> getSeries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "releaseDate") String sortBy) {
        PageRequest request = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
        return seriesService.getSeries(request).map(seriesMapper::map).toList();
    }

}
