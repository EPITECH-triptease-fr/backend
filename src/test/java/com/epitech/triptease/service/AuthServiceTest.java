package com.epitech.triptease.service;

import com.epitech.triptease.entity.User;
import com.epitech.triptease.exception.user.UserConflictException;
import com.epitech.triptease.exception.user.UserNotFoundException;
import com.epitech.triptease.mapper.dto.AuthResponseDTO;
import com.epitech.triptease.mapper.dto.LoginRequestDTO;
import com.epitech.triptease.mapper.dto.PasswordDTO;
import com.epitech.triptease.mapper.dto.SignUpRequestDTO;
import com.epitech.triptease.security.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenProvider tokenProvider;


    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthService(userService, passwordEncoder, authenticationManager, tokenProvider);
    }

    @Test
    void login_ValidCredentials_ReturnsAuthResponseDTO() {
        String username = "testuser";
        String password = "testpassword";
        String token = "testtoken";

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername(username);
        loginRequestDTO.setPassword(password);

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(tokenProvider.generateJwtToken(authentication)).thenReturn(token);

        AuthResponseDTO authResponseDTO = authService.login(loginRequestDTO);

        assertNotNull(authResponseDTO);
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenProvider, times(1)).generateJwtToken(authentication);
    }

    @Test
    void signUp_ValidSignUpRequest_ReturnsAuthResponseDTO() throws UserConflictException {
        String username = "testuser";
        String password = "testpassword";
        String name = "Test User";
        String email = "test@example.com";
        String token = "testtoken";

        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setUsername(username);
        signUpRequestDTO.setPassword(password);
        signUpRequestDTO.setName(name);
        signUpRequestDTO.setEmail(email);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);

        when(userService.hasUserWithUsername(username)).thenReturn(false);
        when(userService.hasUserWithEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn(password);
        when(userService.saveUser(any(User.class))).thenReturn(user);
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(tokenProvider.generateJwtToken(authentication)).thenReturn(token);

        AuthResponseDTO authResponseDTO = authService.signUp(signUpRequestDTO);

        assertNotNull(authResponseDTO);
        verify(userService, times(1)).hasUserWithUsername(username);
        verify(userService, times(1)).hasUserWithEmail(email);
        verify(passwordEncoder, times(1)).encode(password);
        verify(userService, times(1)).saveUser(any(User.class));
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenProvider, times(1)).generateJwtToken(authentication);
    }

    @Test
    void signUp_BadSignUpRequest_ReturnsAuthResponseDTO() {
        String username = "testuser";
        String password = "testpassword";
        String name = "Test User";
        String email = "test@example.com";

        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setUsername(username);
        signUpRequestDTO.setPassword(password);
        signUpRequestDTO.setName(name);
        signUpRequestDTO.setEmail(email);

        when(userService.hasUserWithUsername(username)).thenReturn(false);
        when(userService.hasUserWithEmail(email)).thenReturn(true); // Simulating a conflict with email

        assertThrows(UserConflictException.class, () -> authService.signUp(signUpRequestDTO));

        verify(userService, times(1)).hasUserWithUsername(username);
        verify(userService, times(1)).hasUserWithEmail(email);
        verifyNoMoreInteractions(userService);
        verifyNoInteractions(passwordEncoder, authenticationManager, tokenProvider);
    }

    @Test
    void isGoodPassword_ValidPassword_ReturnsTrue() throws UserNotFoundException {
        Long userId = 1L;
        String password = "password123";
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setUser_id(userId);
        passwordDTO.setPassword(password);

        User user = new User();
        user.setId(userId);
        user.setPassword(passwordEncoder.encode(password));

        when(userService.getUserById(userId)).thenReturn(user);
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);

        boolean result = authService.isGoodPassword(passwordDTO);

        assertTrue(result);

        verify(userService, times(1)).getUserById(userId);
        verify(passwordEncoder, times(1)).matches(password, user.getPassword());
    }

    @Test
    void isGoodPassword_InvalidPassword_ReturnsFalse() throws UserNotFoundException {
        Long userId = 1L;
        String correctPassword = "correctPassword";
        String incorrectPassword = "incorrectPassword";
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setUser_id(userId);
        passwordDTO.setPassword(incorrectPassword);

        User user = new User();
        user.setId(userId);
        user.setPassword(passwordEncoder.encode(correctPassword));

        when(userService.getUserById(userId)).thenReturn(user);
        when(passwordEncoder.matches(incorrectPassword, user.getPassword())).thenReturn(false);

        boolean result = authService.isGoodPassword(passwordDTO);

        assertFalse(result);

        verify(userService, times(1)).getUserById(userId);
        verify(passwordEncoder, times(1)).matches(incorrectPassword, user.getPassword());
    }
}