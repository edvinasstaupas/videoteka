package com.videolibrary.backend.infrastructure.rest.advice;

import com.videolibrary.backend.domain.exception.EntityNotFoundException;
import com.videolibrary.backend.infrastructure.rest.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@Configuration("RestExceptionHandler")
@RequiredArgsConstructor
public class RestExceptionHandler {

    private final HttpServletRequest request;

    private ResponseEntity<ErrorResponse> toResponse(Exception exception, HttpStatus status) {
        return toResponse(exception, status, exception.getMessage());
    }

    private ResponseEntity<ErrorResponse> toResponse(Exception exception, HttpStatus status, String message) {
        ErrorResponse entity = new ErrorResponse();
        entity.setException(exception.getClass().getSimpleName());
        entity.setMessage(message);
        return new ResponseEntity<>(entity, status);
    }

    // 400 bad request
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInvalidObjects(Exception exception) {
        var ex = (MethodArgumentNotValidException) exception;
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
        });
        return toResponse(exception, HttpStatus.BAD_REQUEST, String.join("\n", errors));
    }

    // 400 bad request
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(Exception exception) {
        var ex = (ConstraintViolationException) exception;
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().forEach(error -> {
            String errorMessage = error.getMessageTemplate();
            errors.add(errorMessage);
        });
        return toResponse(exception, HttpStatus.BAD_REQUEST, String.join("\n", errors));
    }

    // 400 bad request
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class,
            HttpRequestMethodNotSupportedException.class, MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception e) {
        return toResponse(e, HttpStatus.BAD_REQUEST);
    }

    // 404 not found
    @ExceptionHandler({NoHandlerFoundException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFound(Exception e) {
        return toResponse(e, HttpStatus.NOT_FOUND);
    }

    // 500 internal server error
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleDefaultExceptions(Exception e) {
        return toResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
