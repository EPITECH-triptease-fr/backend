package com.epitech.triptease.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.epitech.triptease.security.oauth2.OAuth2Provider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenProviderTest {

    @Mock
    private Authentication authentication;

    @InjectMocks
    private TokenProvider tokenProvider;

    private static final String JWT_SECRET = "C'est secret";
    private static final Long JWT_EXPIRE_MINUTES = 18L;
    private static final String USERNAME = "john.doe";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(tokenProvider, "jwtSecret", "C'est secret");
        ReflectionTestUtils.setField(tokenProvider, "jwtExpireMinutes", 18L);

        tokenProvider.init();
    }

    @Test
    void testGenerateJwtToken() {
        // Mock data
        CustomUserDetails userPrincipal = new CustomUserDetails();
        userPrincipal.setId(1L);
        userPrincipal.setName("john");
        userPrincipal.setUsername("john.doe");
        userPrincipal.setPassword("password");
        userPrincipal.setProvider(OAuth2Provider.LOCAL);
        userPrincipal.setAuthorities(Collections.emptyList());
        userPrincipal.setEmail("john@doe.fr");


        when(authentication.getPrincipal()).thenReturn(userPrincipal);

        // Invoke the method
        String jwtToken = tokenProvider.generateJwtToken(authentication);

        // Verify the token
        assertNotNull(jwtToken);

        // Verify the claims
        DecodedJWT decodedJWT = JWT.decode(jwtToken);
        assertEquals(USERNAME, decodedJWT.getSubject());
        assertEquals(1L, decodedJWT.getClaim(SecurityConstants.TOKEN_ID).asLong());
        assertEquals("john@doe.fr", decodedJWT.getClaim(SecurityConstants.TOKEN_MAIL).asString());
        assertNotNull(decodedJWT.getExpiresAt());

        Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
        algorithm.verify(decodedJWT);
    }

    @Test
    void testGetUserNameFromJwtToken() {
        // Mock data
        String jwtToken = generateJwtToken();

        // Invoke the method
        String username = tokenProvider.getUserNameFromJwtToken(jwtToken);

        // Verify the result
        assertEquals(USERNAME, username);
    }

    @Test
    void testValidateJwtToken_ValidToken() {
        // Mock data
        String jwtToken = generateJwtToken();

        // Invoke the method
        boolean isValid = tokenProvider.validateJwtToken(jwtToken);

        // Verify the result
        assertTrue(isValid);
    }

    @Test
    void testValidateJwtToken_InvalidToken() {
        // Mock data
        String invalidJwtToken = "invalidToken";

        // Invoke the method
        boolean isValid = tokenProvider.validateJwtToken(invalidJwtToken);

        // Verify the result
        assertFalse(isValid);
    }

    @Test
    void testDecodeJwt() {
        // Mock data
        String jwtToken = generateJwtToken();

        // Invoke the method
        DecodedJWT decodedJWT = tokenProvider.decodeJwt(jwtToken);

        // Verify the result
        assertNotNull(decodedJWT);
        assertEquals(USERNAME, decodedJWT.getSubject());
        assertEquals(1L, decodedJWT.getClaim(SecurityConstants.TOKEN_ID).asLong());
        assertEquals("john.doe@example.com", decodedJWT.getClaim(SecurityConstants.TOKEN_MAIL).asString());
        assertNotNull(decodedJWT.getExpiresAt());
    }

    private String generateJwtToken() {
        return JWT.create()
                .withSubject(USERNAME)
                .withClaim(SecurityConstants.TOKEN_ID, 1L)
                .withClaim(SecurityConstants.TOKEN_MAIL, "john.doe@example.com")
                .withExpiresAt(Date.from(LocalDateTime.now().plus(Duration.ofMinutes(JWT_EXPIRE_MINUTES)).atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(JWT_SECRET));
    }
}
