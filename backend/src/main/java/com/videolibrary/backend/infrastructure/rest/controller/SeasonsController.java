package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.entity.Season;
import com.videolibrary.backend.domain.service.SeasonService;
import com.videolibrary.backend.infrastructure.rest.convert.SeasonMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateSeasonDto;
import com.videolibrary.backend.infrastructure.rest.dto.SeasonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("seasons")
@RestController
@RequiredArgsConstructor
public class SeasonsController {
    private final SeasonService seasonService;
    private final SeasonMapper seasonMapper;

    @DeleteMapping("{id}")
    public void deleteSeason(@PathVariable Integer id) {
        seasonService.deleteSeason(id);
    }

    @PatchMapping("{id}")
    public SeasonDto updateSeason(@PathVariable Integer id, @RequestBody CreateSeasonDto dto) {
        Season seasons = seasonMapper.map(dto);
        Season entity = seasonService.updateSeason(id, seasons);
        return seasonMapper.map(entity);
    }
}
