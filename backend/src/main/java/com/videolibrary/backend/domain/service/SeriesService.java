package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.dto.SearchDto;
import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.sql.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    private final GenreService genreService;

    public Page<Series> getSeries(PageRequest request, SearchDto searchDto) {
        var genres = searchDto.getGenreIds();
        if (genres == null)
            genres = genreService.findAllIds();
        return seriesRepository.findAllByTitleContainingAndGenresIn(request, searchDto.getTitle(), genres);
    }
}
