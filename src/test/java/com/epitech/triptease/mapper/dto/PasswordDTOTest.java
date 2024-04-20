package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordDTOTest {

    @Test
    void testUserIdGetterAndSetter() {
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setUser_id(123L);
        assertEquals(123L, passwordDTO.getUser_id());
    }

    @Test
    void testPasswordGetterAndSetter() {
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setPassword("password123");
        assertEquals("password123", passwordDTO.getPassword());
    }
}
