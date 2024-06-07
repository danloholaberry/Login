package com.bci.login.dto;


import com.bci.login.model.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class UserDetailResponseTest {

    @Test
    public void testUserDetailResponseFields() {
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        Phone phone = new Phone(123456789L, 1, "56");
        UserDetailResponse userDetailResponse = new UserDetailResponse(userId, now, now, "token",
                true, "John", "john@example.com", "Password1",
                Collections.singletonList(phone));

        assertEquals(userId, userDetailResponse.getId());
        assertEquals(now, userDetailResponse.getCreated());
        assertEquals(now, userDetailResponse.getLastLogin());
        assertEquals("token", userDetailResponse.getToken());
        assertTrue(userDetailResponse.isActive());
        assertEquals("John", userDetailResponse.getName());
        assertEquals("john@example.com", userDetailResponse.getEmail());
        assertEquals("Password1", userDetailResponse.getPassword());
        assertEquals(1, userDetailResponse.getPhones().size());
        assertEquals(phone, userDetailResponse.getPhones().get(0));
    }

}