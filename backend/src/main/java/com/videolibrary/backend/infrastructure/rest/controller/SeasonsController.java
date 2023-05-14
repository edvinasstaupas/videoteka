package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("seasons")
@RestController
@RequiredArgsConstructor
public class SeasonsController {
    private final SeasonService seasonService;

    @DeleteMapping("{id}")
    public void deleteSeason(@PathVariable Integer id) {
        seasonService.deleteSeason(id);
    }
}
