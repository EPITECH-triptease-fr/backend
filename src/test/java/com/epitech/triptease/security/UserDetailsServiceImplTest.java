package com.epitech.triptease.security;

import com.epitech.triptease.entity.User;
import com.epitech.triptease.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    void testLoadUserByUsername_ExistingUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test_user");
        user.setPassword("password");
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setRole("ROLE_USER");

        when(userRepository.findByUsername("test_user")).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername("test_user");

        assertNotNull(userDetails);
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_NonExistingUser() {
        when(userRepository.findByUsername("non_existing_user")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("non_existing_user");
        });
    }

}
