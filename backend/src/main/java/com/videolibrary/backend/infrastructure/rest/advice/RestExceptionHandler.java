package com.videolibrary.backend.infrastructure.rest.advice;

import com.videolibrary.backend.domain.exception.DomainEntityNotFoundException;
import com.videolibrary.backend.infrastructure.rest.dto.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
@Configuration("RestExceptionHandler")
@RequiredArgsConstructor
public class RestExceptionHandler {

    private final ErrorResponseCallback errorCallback;

    private ResponseEntity<ErrorResponse> toResponse(Exception exception, HttpStatus status) {
        return toResponse(exception, status, exception.getMessage());
    }

    // 400 bad request
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInvalidObjects(Exception exception) {
        var ex = (MethodArgumentNotValidException) exception;
        var message = String.join("\n", ex.getBindingResult().getAllErrors().stream().map(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            return fieldName + ": " + errorMessage;
        }).toList());
        return toResponse(exception, HttpStatus.BAD_REQUEST, message);
    }

    // 400 bad request
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(Exception exception) {
        var ex = (ConstraintViolationException) exception;
        var message = String.join("\n", ex.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).toList());
        return toResponse(exception, HttpStatus.BAD_REQUEST, message);
    }

    // 400 bad request
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class, HttpRequestMethodNotSupportedException.class, MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception e) {
        return toResponse(e, HttpStatus.BAD_REQUEST);
    }

    // 403 forbidden
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleAccessDenied(Exception e) {
        return toResponse(e, HttpStatus.FORBIDDEN);
    }

    // 404 not found
    @ExceptionHandler({NoHandlerFoundException.class, DomainEntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFound(Exception e) {
        return toResponse(e, HttpStatus.NOT_FOUND);
    }

    // 409 conflict
    @ExceptionHandler({ObjectOptimisticLockingFailureException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleOptimisticLocking(Exception e) {
        return toResponse(e, HttpStatus.CONFLICT);
    }

    // 500 internal server error
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleDefaultExceptions(Exception e) {
        return toResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> toResponse(Exception exception, HttpStatus status, String message) {
        ErrorResponse entity = new ErrorResponse();
        entity.setException(exception.getClass().getSimpleName());
        entity.setMessage(message);
        errorCallback.accept(exception, status);
        return new ResponseEntity<>(entity, status);
    }
}
