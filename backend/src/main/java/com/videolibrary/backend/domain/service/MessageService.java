package com.videolibrary.backend.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final AuthService authService;

    public void printAuthName() {
        System.out.println(authService.getCurrentUserId() + " (" + authService.getCurrentUserRole() + ")");
    }
}
