package com.epitech.triptease.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityConstantsTest {

    @Test
    public void testRoleUserConstant() {
        assertEquals("ROLE_USER", SecurityConstants.ROLE_USER);
    }

    @Test
    public void testRoleAdminConstant() {
        assertEquals("ROLE_ADMIN", SecurityConstants.ROLE_ADMIN);
    }

    @Test
    public void testJwtRolesNamespaceConstant() {
        assertEquals("role", SecurityConstants.JWT_ROLES_NAMESPACE);
    }

    @Test
    public void testTokenMailConstant() {
        assertEquals("mail", SecurityConstants.TOKEN_MAIL);
    }

    @Test
    public void testTokenIdConstant() {
        assertEquals("id", SecurityConstants.TOKEN_ID);
    }
}
