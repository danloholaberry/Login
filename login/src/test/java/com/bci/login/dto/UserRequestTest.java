package com.bci.login.dto;

import com.bci.login.model.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserRequestTest {

    @Test
    public void testUserRequestFields() {
        Phone phone = new Phone(123456789L, 1, "56");
        UserRequest userRequest = new UserRequest("John Doe", "john.doe@example.com",
                "Password1", Collections.singletonList(phone));

        assertEquals("John Doe", userRequest.getName());
        assertEquals("john.doe@example.com", userRequest.getEmail());
        assertEquals("Password1", userRequest.getPassword());
        assertEquals(1, userRequest.getPhones().size());
        assertEquals(phone, userRequest.getPhones().get(0));
    }

}