package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.infrastructure.rest.convert.EpisodeMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateEpisodeDto;
import com.videolibrary.backend.infrastructure.sql.repository.EpisodeRepository;
import com.videolibrary.backend.infrastructure.sql.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EpisodeService {
    private final SeasonRepository seasonRepository;
    private final EpisodeRepository episodeRepository;
    private final EpisodeMapper episodeMapper;
    private final VideoService videoService;

    public Episode createEpisode(Integer seasonId, CreateEpisodeDto dto) {
        Episode episode = episodeMapper.map(dto);
        episode.setSeason(seasonRepository.findByIdOrThrow(seasonId));
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

}
