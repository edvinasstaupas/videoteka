package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.dto.SearchDto;
import com.videolibrary.backend.domain.entity.Movie;
import com.videolibrary.backend.infrastructure.sql.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    private final GenreService genreService;

    public Page<Movie> getMovies(PageRequest request, SearchDto searchDto) {
        var genres = searchDto.getGenreIds();
        if (genres == null)
            genres = genreService.findAllIds();
        return movieRepository.findAllByTitleContainingAndGenresIn(request, searchDto.getTitle(), genres);
    }
}
