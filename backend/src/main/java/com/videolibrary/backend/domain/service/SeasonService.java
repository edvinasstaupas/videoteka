package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.convert.SeasonMapper;
import com.videolibrary.backend.infrastructure.sql.repository.SeasonRepository;
import com.videolibrary.backend.infrastructure.sql.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeriesRepository seriesRepository;
    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    public Season createSeason(Integer seriesId, Season season) {
        Series series = seriesRepository.findByIdOrThrow(seriesId);
        season.setSeries(series);
        return seasonRepository.save(season);
    }

    public void deleteSeason(Integer id) {
        seasonRepository.deleteById(id);
    }

    public Season updateSeason(Integer id, Season season) {
        Season existingSeason = seasonRepository.findByIdOrThrow(id);
        seasonMapper.update(season, existingSeason);
        return seasonRepository.save(existingSeason);
    }
}
