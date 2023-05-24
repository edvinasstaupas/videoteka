package com.videolibrary.backend.configuration;

import com.videolibrary.backend.domain.entity.Error;
import com.videolibrary.backend.domain.service.UserService;
import com.videolibrary.backend.infrastructure.rest.advice.ErrorResponseCallback;
import com.videolibrary.backend.infrastructure.sql.repository.ErrorRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import java.time.Clock;
import java.time.ZonedDateTime;

@Configuration
public class ExceptionCallbackConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCallbackConfiguration.class);

    private final DataSize traceableExceptionSize;

    public ExceptionCallbackConfiguration(@Value("${traceable-exception-size}") DataSize traceableExceptionSize) {
        this.traceableExceptionSize = traceableExceptionSize;
    }

    @Bean
    @ConditionalOnProperty(value = "persist-exceptions", havingValue = "false", matchIfMissing = true)
    public ErrorResponseCallback logExceptionCallback() {
        return (exception, status) -> {
            String stackTrace = extractStackTrace(exception);
            LOGGER.error("Exception during processing. Status - {}. Exception - {}", status, stackTrace);
        };
    }

    @Bean
    @ConditionalOnProperty(value = "persist-exceptions", havingValue = "true")
    public ErrorResponseCallback persistExceptionCallback(ErrorRepository repository, UserService userService, Clock clock) {
        return (exception, status) -> {
            Error error = new Error();
            error.setStatus(status.value());
            error.setTimestamp(ZonedDateTime.now(clock));
            error.setStackTrace(extractStackTrace(exception));
            error.setUser(userService.getUser());
            repository.save(error);
        };
    }

    private String extractStackTrace(Exception exception) {
        String originalStackTrace = ExceptionUtils.getStackTrace(exception);
        return StringUtils.truncate(originalStackTrace, (int) traceableExceptionSize.toBytes());
    }
}
