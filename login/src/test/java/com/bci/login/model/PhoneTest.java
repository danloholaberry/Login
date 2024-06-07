package com.bci.login.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PhoneTest {

    @Test
    void testUserFields() {
        User user = new User();
        UUID userId = UUID.randomUUID();
        user.setId(userId);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("Password1");
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken("some-token");
        user.setActive(true);

        assertEquals(userId, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("Password1", user.getPassword());
        assertNotNull(user.getCreated());
        assertNotNull(user.getLastLogin());
        assertEquals("some-token", user.getToken());
        assertTrue(user.isActive());
    }

}