package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Video;
import com.videolibrary.backend.infrastructure.rest.dto.CreateVideoDto;
import com.videolibrary.backend.infrastructure.sql.repository.FileResourceRepository;
import com.videolibrary.backend.infrastructure.sql.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final FileResourceRepository fileRepository;

    public Video createVideo(CreateVideoDto dto) {
        Video video = new Video();
        video.setThumbnail(fileRepository.findByIdOrThrow(dto.getThumbnailId()));
        video.setContent(fileRepository.findByIdOrThrow(dto.getContentId()));
        return videoRepository.save(video);
    }
}
