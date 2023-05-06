package com.videolibrary.backend.service;

import com.videolibrary.backend.convert.VideoMapper;
import com.videolibrary.backend.dto.VideoPreview;
import com.videolibrary.backend.repository.EpisodeRepository;
import com.videolibrary.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VideoService {
    private final MovieRepository movieRepository;
    private final EpisodeRepository episodeRepository;
    private final VideoMapper videoMapper;

    public List<VideoPreview> getVideosForMovies(PageRequest request) {
        return movieRepository.findAll(request).map(videoMapper::map).toList();
    }

    public List<VideoPreview> getVideosForEpisodes(PageRequest request) {
        return episodeRepository.findAll(request).map(videoMapper::map).toList();
    }
}
