package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.sql.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;


    public Page<Series> getSeries(PageRequest request, Specification<Series> specification) {
        return seriesRepository.findAll(specification, request);
    }

    public Series createSeries(Series series) {
        return seriesRepository.save(series);
    }
}
