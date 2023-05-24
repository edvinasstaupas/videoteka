package com.videolibrary.backend.infrastructure.service;

import com.videolibrary.backend.domain.entity.History;
import com.videolibrary.backend.domain.service.UserService;
import com.videolibrary.backend.infrastructure.rest.dto.CreateHistoryDto;
import com.videolibrary.backend.infrastructure.sql.repository.HistoryAwareRepository;
import com.videolibrary.backend.infrastructure.sql.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryAwareRepository historyAwareRepository;
    private final HistoryRepository historyRepository;
    private final UserService userService;
    private final Clock clock;

    public Page<History> getHistory(PageRequest request) {
        return historyRepository.findHistoryForUser(userService.getUser().getId(), request);
    }

    public History createHistory(CreateHistoryDto dto) {
        History history = new History();
        history.setUser(userService.getUser());
        history.setHistoryAware(historyAwareRepository.findByIdOrThrow(dto.getHistoryAwareId()));
        history.setTimestamp(ZonedDateTime.now(clock));
        return historyRepository.save(history);
    }
}
