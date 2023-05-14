package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Genre;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.sql.repository.GenreRepository;
import com.videolibrary.backend.infrastructure.sql.repository.SeriesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final GenreRepository genreRepository;

    public Page<Series> getSeries(PageRequest request, Specification<Series> specification) {
        return seriesRepository.findAll(specification, request);
    }

    public Series getSeries(Integer seriesId) {
        return seriesRepository.findById(seriesId).orElseThrow(EntityNotFoundException::new);
    }

    public Series createSeries(Series series, List<Integer> genreIds) {
        List<Genre> genres = genreRepository.findAllById(genreIds);
        series.setGenres(new HashSet<>(genres));
        return seriesRepository.save(series);
    }
}
