package com.videolibrary.backend.infrastructure.service;

import com.videolibrary.backend.domain.entity.FileResource;
import com.videolibrary.backend.property.StorageProperties;
import jakarta.persistence.PostRemove;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public class FileEventListener {
    private final StorageProperties properties;

    @PostRemove
    void deleteFile(FileResource file) {
        String filename = file.getId().toString();
        Path resourcePath = properties.getRootStoragePath().resolve(filename);
        try {
            Files.deleteIfExists(resourcePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file " + filename, e);
        }
    }
}
