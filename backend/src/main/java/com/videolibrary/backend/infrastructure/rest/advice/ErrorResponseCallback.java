package com.videolibrary.backend.infrastructure.rest.advice;

import org.springframework.http.HttpStatus;

public interface ErrorResponseCallback {

    void accept(Exception exception, HttpStatus status);
}
