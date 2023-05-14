package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.sql.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final SeasonService seasonService;
    private final EpisodeRepository episodeRepository;

    public List<Episode> createEpisodes(Integer seriesId, Integer seasonId, List<Episode> episodes) {
        Season season = seasonService.findBySeriesIdAndId(seriesId, seasonId);
        Series series = season.getSeries();
        series.setLastEpisodeReleaseDate(getLastEpisodeReleaseDate(episodes, series));
        updateEpisodes(episodes, season);
        return episodeRepository.saveAll(episodes);
    }

    private LocalDate getLastEpisodeReleaseDate(List<Episode> episodes, Series series) {
        LocalDate currentLastReleaseDate = series.getLastEpisodeReleaseDate();
        LocalDate newEpisodesLastReleaseDate = episodes.stream()
                .map(Episode::getReleaseDate)
                .max(Comparator.naturalOrder())
                .orElse(null);
        return ObjectUtils.max(currentLastReleaseDate, newEpisodesLastReleaseDate);
    }

    private void updateEpisodes(List<Episode> episodes, Season season) {
        int currentEpisodeCount = season.getEpisodes().size();
        for (Episode episode : episodes) {
            ++currentEpisodeCount;
            episode.setSeason(season);
            episode.setNumberInSeason(currentEpisodeCount);
        }
    }
}
