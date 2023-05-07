package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.dto.VideoPreview;
import com.videolibrary.backend.infrastructure.sql.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    public Page<Series> getSeries(PageRequest request) {
        return seriesRepository.findAll(request);
    }
}
