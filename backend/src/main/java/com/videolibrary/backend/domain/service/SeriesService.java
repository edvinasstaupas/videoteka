package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.dto.SearchDto;
import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.sql.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    public Page<Series> getSeriesOrderedByEpisodeReleaseDate(PageRequest request, SearchDto searchDto) {
        if (searchDto.getGenreIds() == null)
            return seriesRepository.getSeries(request, searchDto.getTitle());
        else
            return seriesRepository.getSeriesFilterGenreIds(request, searchDto.getTitle(), searchDto.getGenreIds());
    }
}
