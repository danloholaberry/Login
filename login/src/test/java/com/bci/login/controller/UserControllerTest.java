package com.bci.login.controller;


import com.bci.login.dto.UserDetailResponse;
import com.bci.login.dto.UserRequest;
import com.bci.login.dto.UserResponse;
import com.bci.login.exception.InvalidEmailException;
import com.bci.login.model.Phone;
import com.bci.login.model.User;
import com.bci.login.service.UserService;
import com.bci.login.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Test
    public void testRegisterUser_Success() throws Exception {

        UserRequest userRequest = new UserRequest();
        userRequest.setName("Test User");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("TestPassword1");
        userRequest.setPhones(Collections.singletonList(new Phone(12345678L, 1, "91")));

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhones(userRequest.getPhones());
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(JwtUtil.generateToken(userRequest.getEmail()));
        user.setActive(true);

        when(userService.saveUser(any(User.class))).thenReturn(user);

        ResponseEntity<UserResponse> response = userController.registerUser(userRequest);

        assertTrue(response.getStatusCode().equals(HttpStatus.OK));

    }


    @Test
    public void testGetUserById_UserFound() throws Exception {

        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("TestPassword1");
        user.setPhones(Collections.singletonList(new Phone(12345678L, 1L, 1, "91")));
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(JwtUtil.generateToken(user.getEmail()));
        user.setActive(true);

        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        ResponseEntity<UserDetailResponse> response = userController.getUserById(userId);

        assertTrue(response.getStatusCode().equals(HttpStatus.OK));

    }

    @Test
    public void testGetUserById_UserNotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        UUID userId = UUID.randomUUID();

        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}