package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.domain.service.EpisodeService;
import com.videolibrary.backend.infrastructure.rest.convert.EpisodeMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateEpisodeDto;
import com.videolibrary.backend.infrastructure.rest.dto.EpisodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("episodes")
@RestController
@RequiredArgsConstructor
public class EpisodesController {
    private final EpisodeService episodeService;
    private final EpisodeMapper episodeMapper;

    @GetMapping("{id}")
    public EpisodeDto getEpisode(@PathVariable Integer id) {
        Episode episode = episodeService.getEpisode(id);
        return episodeMapper.map(episode);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public void deleteEpisode(@PathVariable Integer id) {
        episodeService.deleteEpisode(id);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public EpisodeDto updateEpisode(@PathVariable Integer id, @RequestBody CreateEpisodeDto dto) {
        Episode entity = episodeService.updateEpisode(id, dto);
        return episodeMapper.map(entity);
    }

}
