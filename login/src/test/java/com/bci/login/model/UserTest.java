package com.bci.login.model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Test
    public void testPhoneFields() {
        Phone phone = new Phone();
        phone.setId(1L);
        phone.setNumber(123456789L);
        phone.setCitycode(1);
        phone.setCountrycode("56");

        assertEquals(1L, phone.getId());
        assertEquals(123456789L, phone.getNumber());
        assertEquals(1, phone.getCitycode());
        assertEquals("56", phone.getCountrycode());
    }

}