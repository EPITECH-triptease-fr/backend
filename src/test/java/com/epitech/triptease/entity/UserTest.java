package com.epitech.triptease.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void testUserEntity() {
        // Set up test data
        Long id = 1L;
        String username = "testuser";
        String password = "testpassword";
        String name = "John Doe";
        String email = "john.doe@example.com";
        String role = "ROLE_USER";
        String provider = "LOCAL";

        // Create a User instance
        User user = new User(id, username, password, name, email, role, provider);

        // Test the User instance
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(role, user.getRole());
        assertEquals(provider, user.getProvider());
    }
}
