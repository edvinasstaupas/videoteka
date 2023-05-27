package com.videolibrary.backend.configuration;

import com.videolibrary.backend.domain.entity.Error;
import com.videolibrary.backend.domain.service.UserService;
import com.videolibrary.backend.infrastructure.rest.advice.ErrorResponseCallback;
import com.videolibrary.backend.infrastructure.sql.repository.ErrorRepository;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZonedDateTime;

@Configuration
public class ExceptionCallbackConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCallbackConfiguration.class);

    @Bean
    @ConditionalOnProperty(value = "logging.persist-exceptions", havingValue = "false", matchIfMissing = true)
    public ErrorResponseCallback logExceptionCallback() {
        return (exception, status) -> {
            String stackTrace = ExceptionUtils.getStackTrace(exception);
            LOGGER.error("Exception during processing. Status - {}. Exception - {}", status, stackTrace);
        };
    }

    @Bean
    @ConditionalOnProperty(value = "logging.persist-exceptions", havingValue = "true")
    public ErrorResponseCallback persistExceptionCallback(ErrorRepository repository, UserService userService, Clock clock) {
        return (exception, status) -> {
            Error error = new Error();
            error.setStatus(status.value());
            error.setTimestamp(ZonedDateTime.now(clock));
            error.setStackTrace(ExceptionUtils.getStackTrace(exception));
            error.setUser(userService.getUser());
            repository.save(error);
        };
    }

}
