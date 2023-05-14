package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.convert.SeasonMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeasonDto;
import com.videolibrary.backend.infrastructure.sql.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;
    private final SeriesService seriesService;
    private final SeasonMapper seasonMapper;


    public Season createSeason(Integer seriesId, CreateSeasonDto dto) {
        Season season = seasonMapper.map(dto);
        Series series = seriesService.getSeries(seriesId);
        season.setSeries(series);
        return seasonRepository.save(season);
    }

   public Season findBySeriesIdAndId(Integer seriesId, Integer seasonId) {
        return seasonRepository.findBySeriesIdAndId(seriesId, seasonId);
   }
}
