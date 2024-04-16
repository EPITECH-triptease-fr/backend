package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthResponseDTOTest {

    @Test
    void testAuthResponseDTO() {
        AuthResponseDTO authResponse = new AuthResponseDTO("yourAccessToken");

        String accessToken = authResponse.accessToken();

        assertEquals("yourAccessToken", accessToken);
    }
}
