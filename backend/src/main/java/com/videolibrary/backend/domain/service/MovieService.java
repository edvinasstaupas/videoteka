package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Movie;
import com.videolibrary.backend.infrastructure.rest.dto.VideoPreview;
import com.videolibrary.backend.infrastructure.sql.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Page<Movie> getMovies(PageRequest request) {
        return movieRepository.findAll(request);
    }
}
