package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.infrastructure.service.FileStorageService;
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

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileStorageService storageService;

    @GetMapping("{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public String upload(@RequestParam("file") MultipartFile file) {
        return storageService.store(file).toString();
    }

    @DeleteMapping("{filename}")
    public void delete(@PathVariable String filename) {
        storageService.delete(filename);
    }
}
