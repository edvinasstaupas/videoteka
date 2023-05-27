package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.infrastructure.rest.convert.EpisodeMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateEpisodeDto;
import com.videolibrary.backend.infrastructure.sql.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EpisodeService {
    private final SeasonService seasonService;
    private final EpisodeRepository episodeRepository;
    private final EpisodeMapper episodeMapper;
    private final VideoService videoService;

    public Episode createEpisode(Integer seasonId, CreateEpisodeDto dto) {
        Episode episode = episodeMapper.map(dto);
        episode.setSeason(seasonService.getSeason(seasonId));
        episode.setVideo(videoService.createVideo(dto.getVideo()));
        return episodeRepository.save(episode);
    }

    public void deleteEpisode(Integer id) {
        episodeRepository.deleteById(id);
    }

    public Episode updateEpisode(Integer id, CreateEpisodeDto dto) {
        Episode existingEpisode = episodeRepository.findByIdOrThrow(id);
        Episode episode = episodeMapper.map(dto);
        episodeMapper.update(episode, existingEpisode);
        return episodeRepository.save(existingEpisode);
    }

    public Episode getEpisode(Integer id) {
        return episodeRepository.findByIdOrThrow(id);
    }
}
