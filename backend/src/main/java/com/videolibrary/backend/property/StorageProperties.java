package com.videolibrary.backend.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;

@Getter
@Setter
@ConfigurationProperties("storage")
public class StorageProperties {
    private Path rootStoragePath;
}
