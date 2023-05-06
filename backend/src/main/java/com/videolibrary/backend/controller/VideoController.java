package com.videolibrary.backend.controller;

import com.videolibrary.backend.dto.VideoPreview;
import com.videolibrary.backend.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/videos")
    public List<VideoPreview> getVideos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "releaseDate") String sortBy) {
        PageRequest request = PageRequest.of(page, size, Sort.by(sortBy));
        return videoService.getVideos(request);
    }
}