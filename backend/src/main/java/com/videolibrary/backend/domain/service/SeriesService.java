package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    public Page<Series> getSeriesOrderedByEpisodeReleaseDate(PageRequest request) {
        return seriesRepository.asd(request);
    }
}
