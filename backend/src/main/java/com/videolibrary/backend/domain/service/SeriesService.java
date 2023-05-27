package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.convert.SeriesMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeriesDto;
import com.videolibrary.backend.infrastructure.sql.repository.FileResourceRepository;
import com.videolibrary.backend.infrastructure.sql.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final GenreService genreService;
    private final SeriesMapper seriesMapper;
    private final FileResourceRepository fileRepository;

    public Page<Series> getSeries(PageRequest request, Specification<Series> specification) {
        return seriesRepository.findAll(specification, request);
    }

    public Series createSeries(CreateSeriesDto dto) {
        Series series = seriesMapper.map(dto);
        series.setGenres(genreService.findAllById(dto.getGenreIds()));
        series.setThumbnail(fileRepository.findByIdOrThrow(dto.getThumbnailId()));
        return seriesRepository.save(series);
    }

    public void deleteSeries(Integer id) {
        seriesRepository.deleteById(id);
    }

    public Series updateSeries(Integer id, CreateSeriesDto dto, Integer version) {
        Series series = new Series();
        seriesMapper.update(seriesRepository.findByIdOrThrow(id), series);
        seriesMapper.update(mapSeriesFromDto(dto), series);
        if (!isForce(version)) {
            series.setVersion(version);
        }
        return seriesRepository.save(series);
    }

    private Series mapSeriesFromDto(CreateSeriesDto dto) {
        Series dtoSeries = seriesMapper.map(dto);
        List<Integer> genreIds = dto.getGenreIds();
        if (genreIds != null)
            dtoSeries.setGenres(genreService.findAllById(genreIds));
        return dtoSeries;
    }

    private boolean isForce(Integer version) {
        return version == null;
    }
}
