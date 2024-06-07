package com.bci.login.exception;


import com.bci.login.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class LoginExceptionHandlerTest {

    @Test
    public void testHandleUserAlreadyExistsException() {
        LoginExceptionHandler globalExceptionHandler = new LoginExceptionHandler();
        UserAlreadyExistsException exception = new UserAlreadyExistsException("User already exists");
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleUserAlreadyExistsException(exception);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);
    }

}