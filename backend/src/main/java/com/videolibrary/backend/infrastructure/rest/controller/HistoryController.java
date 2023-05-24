package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.entity.History;
import com.videolibrary.backend.infrastructure.rest.convert.HistoryMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateHistoryDto;
import com.videolibrary.backend.infrastructure.rest.dto.HistoryDto;
import com.videolibrary.backend.infrastructure.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryMapper historyMapper;
    private final HistoryService historyService;

    @GetMapping
    public Page<HistoryDto> getHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest request = PageRequest.of(page, size);
        Page<History> history = historyService.getHistory(request);
        return history.map(History::getHistoryAware).map(historyMapper::map);
    }

    @PostMapping
    public Integer createHistory(@RequestBody CreateHistoryDto dto) {
        History entity = historyService.createHistory(dto);
        return entity.getId();
    }
}
