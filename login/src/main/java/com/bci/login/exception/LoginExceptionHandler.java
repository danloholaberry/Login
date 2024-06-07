package com.bci.login.exception;

import com.bci.login.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LoginExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .details(ex.getMessage())
                .codigo(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(InvalidEmailException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .details(ex.getMessage())
                .codigo(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(InvalidPasswordException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .details(ex.getMessage())
                .codigo(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
