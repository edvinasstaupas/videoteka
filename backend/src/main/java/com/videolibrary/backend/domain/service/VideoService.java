package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Video;
import com.videolibrary.backend.infrastructure.rest.dto.CreateVideoDto;
import com.videolibrary.backend.infrastructure.service.FileResourceService;
import com.videolibrary.backend.infrastructure.sql.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final FileResourceService fileResourceService;

    public Video createVideo(CreateVideoDto dto) {
        Video video = new Video();
        video.setThumbnail(fileResourceService.getFileResource(dto.getThumbnailId()));
        video.setContent(fileResourceService.getFileResource(dto.getContentId()));
        return videoRepository.save(video);
    }
}
