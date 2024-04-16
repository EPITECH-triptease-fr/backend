package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginRequestDTOTest {

    @Test
    void testLoginRequestDTO() {
        LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setUsername("sampleUsername");
        loginRequest.setPassword("samplePassword");

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        assertEquals("sampleUsername", username);
        assertEquals("samplePassword", password);
    }
}
