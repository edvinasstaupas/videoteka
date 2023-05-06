package com.videolibrary.backend.service;

import com.videolibrary.backend.convert.VideoMapper;
import com.videolibrary.backend.dto.VideoPreview;
import com.videolibrary.backend.entities.Episode;
import com.videolibrary.backend.entities.Movie;
import com.videolibrary.backend.repository.EpisodeRepository;
import com.videolibrary.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class VideoService {
    private final MovieRepository movieRepository;
    private final EpisodeRepository episodeRepository;
    private final VideoMapper videoMapper;

    public List<VideoPreview> getVideos(PageRequest request) {
        Page<Movie> movies = movieRepository.findAll(request);
        Page<Episode> episodes = episodeRepository.findAll(request);
        return Stream.concat(
                movies.stream().map(videoMapper::map),
                episodes.stream().map(videoMapper::map)
        ).toList();
    }
}
