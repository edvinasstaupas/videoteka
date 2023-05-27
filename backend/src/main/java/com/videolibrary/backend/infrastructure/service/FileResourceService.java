package com.videolibrary.backend.infrastructure.service;

import com.videolibrary.backend.domain.entity.FileResource;
import com.videolibrary.backend.infrastructure.sql.repository.FileResourceRepository;
import com.videolibrary.backend.property.StorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class FileResourceService {
    private final StorageProperties properties;
    private final FileResourceRepository fileRepository;

    public UUID store(MultipartFile file) {
        FileResource saved = saveEntity(file);
        Path destinationPath = properties.getRootStoragePath().resolve(saved.getId().toString());
        try {
            Files.createDirectories(destinationPath.getParent());
            file.transferTo(destinationPath);
            return saved.getId();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename() + ". To " + destinationPath, e);
        }
    }

    public FileResource getFileResource(UUID id) {
        return fileRepository.findByIdOrThrow(id);
    }

    public Resource loadAsResource(UUID id) {
        FileResource resource = fileRepository.findByIdOrThrow(id);
        Path resourcePath = properties.getRootStoragePath().resolve(resource.getId().toString());
        return UrlResource.from(resourcePath.toUri());
    }

    public void delete(UUID id) {
        fileRepository.deleteById(id);
    }

    private FileResource saveEntity(MultipartFile file) {
        FileResource resource = new FileResource();
        resource.setId(UUID.randomUUID());
        resource.setContentType(file.getContentType());
        return fileRepository.save(resource);
    }
}
