package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.entity.Episode;
import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.service.EpisodeService;
import com.videolibrary.backend.domain.service.SeasonService;
import com.videolibrary.backend.infrastructure.rest.convert.EpisodeMapper;
import com.videolibrary.backend.infrastructure.rest.convert.SeasonMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateEpisodeDto;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeasonDto;
import com.videolibrary.backend.infrastructure.rest.dto.EpisodeDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeasonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("seasons")
@RestController
@RequiredArgsConstructor
public class SeasonsController {
    private final SeasonService seasonService;
    private final SeasonMapper seasonMapper;
    private final EpisodeMapper episodeMapper;
    private final EpisodeService episodeService;

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public void deleteSeason(@PathVariable Integer id) {
        seasonService.deleteSeason(id);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public SeasonDto updateSeason(@PathVariable Integer id, @RequestBody CreateSeasonDto dto) {
        Season entity = seasonService.updateSeason(id, dto);
        return seasonMapper.map(entity);
    }

    @PostMapping("{seasonId}/episodes")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public EpisodeDto createEpisode(@PathVariable Integer seasonId, @RequestBody CreateEpisodeDto dto) {
        Episode entity = episodeService.createEpisode(seasonId, dto);
        return episodeMapper.map(entity);
    }
}
