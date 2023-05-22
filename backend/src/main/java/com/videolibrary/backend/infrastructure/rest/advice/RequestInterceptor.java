package com.videolibrary.backend.infrastructure.rest.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.Principal;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger("AuditLog");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Principal principal = request.getUserPrincipal();
        Collection<GrantedAuthority> authorities = ((JwtAuthenticationToken) principal).getAuthorities();

        LOGGER.info("User: {}, Authorities: {}, Called method: {}", principal.getName(), authorities, handler);
        return true;
    }
}
