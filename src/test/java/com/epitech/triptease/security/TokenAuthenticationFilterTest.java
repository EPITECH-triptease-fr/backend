package com.epitech.triptease.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class




TokenAuthenticationFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private TokenProvider tokenProvider;

    @InjectMocks
    private TokenAuthenticationFilter filter;

    @Test
    void testDoFilterInternal_ValidToken() throws ServletException, IOException {
        // Mock data
        String jwtToken = "validToken";
        String username = "john.doe";
        CustomUserDetails userPrincipal = new CustomUserDetails();
        userPrincipal.setId(1L);
        userPrincipal.setUsername("john.doe");
        userPrincipal.setPassword("password");
        userPrincipal.setAuthorities(Collections.emptyList());
        userPrincipal.setEmail("john@doe.fr");

        Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
        when(tokenProvider.validateJwtToken(jwtToken)).thenReturn(true);
        when(tokenProvider.getUserNameFromJwtToken(jwtToken)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userPrincipal);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwtToken);

        // Mock the SecurityContextHolder and its related methods
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        // Invoke the filter
        filter.doFilterInternal(request, response, chain);

        // Verify the filter chain is called
        verify(chain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_InvalidToken() throws ServletException, IOException {
        // Mock data
        String invalidJwtToken = "invalidToken";
        when(tokenProvider.validateJwtToken(invalidJwtToken)).thenReturn(false);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + invalidJwtToken);

        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        // Invoke the filter
        filter.doFilterInternal(request, response, chain);

        // Verify the authentication is not set
        verify(SecurityContextHolder.getContext(), never()).setAuthentication(any());

        // Verify the filter chain is called
        verify(chain).doFilter(request, response);
    }
}
