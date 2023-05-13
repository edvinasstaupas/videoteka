package com.videolibrary.backend.domain.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public void printAuthName() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
