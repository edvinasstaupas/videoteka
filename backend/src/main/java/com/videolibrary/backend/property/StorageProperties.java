package com.videolibrary.backend.property;

import com.videolibrary.backend.infrastructure.rest.dto.FileType;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.util.Map;

@Setter
@ConfigurationProperties("storage")
public class StorageProperties {
    private Path rootStoragePath = Path.of("uploads");
    private Map<FileType, String> storagePaths = Map.of(
            FileType.VIDEO, "videos",
            FileType.THUMBNAIL, "thumbnails"
    );

    public Path getStoragePath(FileType type) {
        return rootStoragePath.resolve(storagePaths.get(type));
    }
}
