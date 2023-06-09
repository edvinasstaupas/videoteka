package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.convert.SeasonMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeasonDto;
import com.videolibrary.backend.infrastructure.sql.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SeasonService {
    private final SeriesService seriesService;
    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    public Season createSeason(Integer seriesId, CreateSeasonDto dto) {
        Series series = seriesService.getSeries(seriesId);
        Season season = seasonMapper.map(dto);
        season.setSeries(series);
        return seasonRepository.save(season);
    }

    public void deleteSeason(Integer id) {
        seasonRepository.deleteById(id);
    }

    public Season updateSeason(Integer id, CreateSeasonDto dto) {
        Season existingSeason = seasonRepository.findByIdOrThrow(id);
        Season season = seasonMapper.map(dto);
        seasonMapper.update(season, existingSeason);
        return seasonRepository.save(existingSeason);
    }

    public Season getSeason(Integer id) {
        return seasonRepository.findByIdOrThrow(id);
    }
}
