package com.bci.login.service;

import com.bci.login.exception.UserAlreadyExistsException;
import com.bci.login.model.User;
import com.bci.login.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser_UserAlreadyExists() {
        User existingUser = new User();
        existingUser.setEmail("test@example.com");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(existingUser));

        User newUser = new User();
        newUser.setEmail("test@example.com");

        assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(newUser));
    }

    @Test
    public void testSaveUser_Success() {
        User newUser = new User();
        newUser.setEmail("test@example.com");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(newUser);

        User savedUser = userService.saveUser(newUser);

        assertNotNull(savedUser);
        assertEquals("test@example.com", savedUser.getEmail());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> user = userService.getUserById(userId);

        assertFalse(user.isPresent());
    }

    @Test
    public void testGetUserById_UserFound() {
        UUID userId = UUID.randomUUID();
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("test@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        Optional<User> user = userService.getUserById(userId);

        assertTrue(user.isPresent());
        assertEquals("test@example.com", user.get().getEmail());
    }

}