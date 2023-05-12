package com.videolibrary.backend.infrastructure.service;

import com.videolibrary.backend.property.StorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileStorageService {
    private final StorageProperties properties;

    public UUID store(MultipartFile file) {
        UUID fileId = UUID.randomUUID();
        Path destinationPath = properties.getRootStoragePath().resolve(fileId.toString());
        try {
            Files.createDirectories(destinationPath.getParent());
            file.transferTo(destinationPath);
            return fileId;
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to store file " + file.getOriginalFilename() + ". To " + destinationPath, e);
        }
    }

    public Resource loadAsResource(String filename) {
        Path resourcePath = properties.getRootStoragePath().resolve(filename);
        return UrlResource.from(resourcePath.toUri());
    }
}
