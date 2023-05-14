package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.sql.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;
    private final SeriesService seriesService;

    public Season createSeason(Integer seriesId, Season season) {
        Series series = seriesService.getSeries(seriesId);
        season.setSeries(series);
        return seasonRepository.save(season);
    }

   public Season findBySeriesIdAndId(Integer seriesId, Integer seasonId) {
        return seasonRepository.findBySeriesIdAndId(seriesId, seasonId);
   }

    public void deleteSeason(Integer id) {
        seasonRepository.deleteById(id);
    }
}
