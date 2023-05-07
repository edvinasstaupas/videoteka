package com.videolibrary.backend.controller;

import com.videolibrary.backend.dto.FileType;
import com.videolibrary.backend.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
    private final FileStorageService storageService;

    @GetMapping("{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, @RequestParam("type") FileType type) {
        Resource file = storageService.loadAsResource(filename, type);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping
    public UUID upload(@RequestParam("file") MultipartFile file, @RequestParam("type") FileType type) {
        return storageService.store(file, type);
    }
}
