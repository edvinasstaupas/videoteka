package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("episodes")
@RestController
@RequiredArgsConstructor
public class EpisodesController {
    private final EpisodeService episodeService;

    @DeleteMapping("{id}")
    public void deleteEpisode(@PathVariable Integer id) {
        episodeService.deleteEpisode(id);
    }
}
