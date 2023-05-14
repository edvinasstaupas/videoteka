package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.sql.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final SeasonService seasonService;
    private final EpisodeRepository episodeRepository;

    public Episode createEpisode(Integer seriesId, Integer seasonId, Episode episode) {
        Season season = seasonService.findBySeriesIdAndId(seriesId, seasonId);
        Series series = season.getSeries();
        series.setLastEpisodeReleaseDate(getLastEpisodeReleaseDate(episode, series));
        updateEpisode(episode, season);
        return episodeRepository.save(episode);
    }

    private LocalDate getLastEpisodeReleaseDate(Episode episode, Series series) {
        return ObjectUtils.max(series.getLastEpisodeReleaseDate(), episode.getReleaseDate());
    }

    private void updateEpisode(Episode episode, Season season) {
        episode.setSeason(season);
        episode.setNumberInSeason(season.getEpisodes().size() + 1);
    }
}
