package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.convert.EpisodeMapper;
import com.videolibrary.backend.infrastructure.sql.repository.EpisodeRepository;
import com.videolibrary.backend.infrastructure.sql.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final SeasonRepository seasonRepository;
    private final EpisodeRepository episodeRepository;
    private final EpisodeMapper episodeMapper;

    public Episode createEpisode(Integer seasonId, Episode episode) {
        Season season = seasonRepository.findByIdOrThrow(seasonId);
        Series series = season.getSeries();
        series.setLastEpisodeReleaseDate(getLastEpisodeReleaseDate(episode, series));
        updateEpisode(episode, season);
        return episodeRepository.save(episode);
    }

    public void deleteEpisode(Integer id) {
        episodeRepository.deleteById(id);
    }

    public Episode updateEpisode(Integer id, Episode episode) {
        Episode existingEpisode = episodeRepository.findByIdOrThrow(id);
        boolean changedReleaseDate = !Objects.equals(episode.getReleaseDate(), existingEpisode.getReleaseDate());

        episodeMapper.update(episode, existingEpisode);
        if (changedReleaseDate) {
            Series series = existingEpisode.getSeason().getSeries();
            series.setLastEpisodeReleaseDate(getLastEpisodeReleaseDate(existingEpisode, series));
        }
        return episodeRepository.save(existingEpisode);
    }

    private LocalDate getLastEpisodeReleaseDate(Episode episode, Series series) {
        return ObjectUtils.max(series.getLastEpisodeReleaseDate(), episode.getReleaseDate());
    }

    private void updateEpisode(Episode episode, Season season) {
        episode.setSeason(season);
        episode.setNumberInSeason(season.getEpisodes().size() + 1);
    }
}
