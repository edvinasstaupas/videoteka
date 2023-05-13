package com.videolibrary.backend.domain.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public String GetCurrentUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
