package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.infrastructure.service.FileResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
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
