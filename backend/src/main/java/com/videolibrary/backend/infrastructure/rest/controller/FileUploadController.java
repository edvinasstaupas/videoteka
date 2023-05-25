package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.infrastructure.service.FileResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileResourceService storageService;

    @GetMapping("{id}")
    public ResponseEntity<Resource> serveFile(@PathVariable UUID id) {
        Resource file = storageService.loadAsResource(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .body(file);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public String upload(@RequestParam("file") MultipartFile file) {
        return storageService.store(file).toString();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public void delete(@PathVariable UUID id) {
        storageService.delete(id);
    }
}
