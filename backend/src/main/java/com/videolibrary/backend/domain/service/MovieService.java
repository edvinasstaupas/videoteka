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

    public Page<Movie> getMovies(PageRequest request, SearchDto searchDto) {
        if (searchDto.getGenreIds() == null)
            return movieRepository.findAllByTitleLike(request, searchDto.getTitle());
        else
            return movieRepository.findAllByTitleLikeAndGenresIn(request, searchDto.getTitle(), searchDto.getGenreIds());
    }
}
