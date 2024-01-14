package com.devfadi.ecommercewebsite.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorData> handleInvalidTokenException(InvalidTokenException ex) {
        log.error("Invalid token error: {}", ex.getMessage());
        ErrorData error = ErrorData.builder().status(HttpStatus.UNAUTHORIZED.value())
                                   .message("Invalid or missing token: " + ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserInfoException.class)
    public ResponseEntity<ErrorData> handleUserInfoException(UserInfoException ex) {
        log.error("User info error: {}", ex.getMessage());
        ErrorData error = ErrorData.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                   .message("Error fetching user information: " + ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorData> handleException(Exception ex) {
        log.error("General error: {}", ex.getMessage());
        ErrorData error = ErrorData.builder().status(HttpStatus.BAD_REQUEST.value())
                                   .message("Error processing request: " + ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}