package com.videolibrary.backend.domain.entity;

import com.videolibrary.backend.infrastructure.service.FileEventListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(FileEventListener.class)
public class FileResource {
    @Id
    private UUID id;

    private String contentType;
}
