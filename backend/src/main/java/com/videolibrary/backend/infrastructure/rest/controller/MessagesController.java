package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.service.MessageService;
import com.videolibrary.backend.infrastructure.rest.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Temporary controller for testing/debugging authorization
 */
@RestController
@RequestMapping("messages")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
class MessagesController {

    private final MessageService messageService;

    @GetMapping(value = "/public")
    public Message publicEndpoint() {
        return new Message("All good. You DO NOT need to be authenticated to call /messages/public.");
    }

    @GetMapping(value = "/user")
    public Message privateEndpoint() {
        messageService.printAuthName();
        return new Message("All good. You can see this because you are Authenticated with user role.");
    }

    @GetMapping(value = "/admin")
    public Message privateScopedEndpoint() {
        messageService.printAuthName();
        return new Message("All good. You can see this because you are Authenticated with admin role");
    }
}