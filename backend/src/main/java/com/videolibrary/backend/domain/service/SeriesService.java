package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.convert.SeriesMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeriesDto;
import com.videolibrary.backend.infrastructure.sql.repository.FileResourceRepository;
import com.videolibrary.backend.infrastructure.sql.repository.GenreRepository;
import com.videolibrary.backend.infrastructure.sql.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final GenreRepository genreRepository;
    private final SeriesMapper seriesMapper;
    private final FileResourceRepository fileRepository;

    public Page<Series> getSeries(PageRequest request, Specification<Series> specification) {
        return seriesRepository.findAll(specification, request);
    }

    public Series createSeries(CreateSeriesDto series) {
        return seriesRepository.save(mapSeries(series));
    }

    public void deleteSeries(Integer id) {
        seriesRepository.deleteById(id);
    }

    public Series updateSeries(Integer id, CreateSeriesDto dto) {
        Series existingSeries = seriesRepository.findByIdOrThrow(id);
        Series series = mapSeries(dto);
        seriesMapper.update(series, existingSeries);
        return seriesRepository.save(existingSeries);
    }

    private Series mapSeries(CreateSeriesDto dto) {
        Series series = seriesMapper.map(dto);
        List<Integer> genreIds = dto.getGenreIds();
        if (genreIds != null)
            series.setGenres(new HashSet<>(genreRepository.findAllById(genreIds)));

        UUID thumbnailId = dto.getThumbnailId();
        if (thumbnailId != null)
            series.setThumbnail(fileRepository.findByIdOrThrow(thumbnailId));
        return series;
    }
}
