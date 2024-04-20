package com.epitech.triptease.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityConstantsTest {

    @Test
    void testRoleUserConstant() {
        assertEquals("ROLE_USER", SecurityConstants.ROLE_USER);
    }

    @Test
    void testRoleAdminConstant() {
        assertEquals("ROLE_ADMIN", SecurityConstants.ROLE_ADMIN);
    }

    @Test
    void testJwtRolesNamespaceConstant() {
        assertEquals("role", SecurityConstants.JWT_ROLES_NAMESPACE);
    }

    @Test
    void testTokenMailConstant() {
        assertEquals("mail", SecurityConstants.TOKEN_MAIL);
    }

    @Test
    void testTokenIdConstant() {
        assertEquals("id", SecurityConstants.TOKEN_ID);
    }
}
