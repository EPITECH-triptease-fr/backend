package com.epitech.triptease.controller;

import com.epitech.triptease.exception.user.UserConflictException;
import com.epitech.triptease.exception.user.UserNotFoundException;
import com.epitech.triptease.mapper.dto.AuthResponseDTO;
import com.epitech.triptease.mapper.dto.LoginRequestDTO;
import com.epitech.triptease.mapper.dto.PasswordDTO;
import com.epitech.triptease.mapper.dto.SignUpRequestDTO;
import com.epitech.triptease.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(authService);
    }

    @Test
    void login_ValidLoginRequest_ReturnsAuthResponseDTO() {
        LoginRequestDTO loginRequest = new LoginRequestDTO();
        AuthResponseDTO expectedAuthResponseDTO = new AuthResponseDTO("token");

        when(authService.login(loginRequest)).thenReturn(expectedAuthResponseDTO);

        AuthResponseDTO resultAuthResponseDTO = authController.login(loginRequest);

        assertEquals(expectedAuthResponseDTO, resultAuthResponseDTO);
        verify(authService, times(1)).login(loginRequest);
    }

    @Test
    void signUp_ValidSignUpRequest_ReturnsAuthResponseDTO() throws UserConflictException {
        SignUpRequestDTO signUpRequest = new SignUpRequestDTO();
        AuthResponseDTO expectedAuthResponseDTO = new AuthResponseDTO("token");

        when(authService.signUp(signUpRequest)).thenReturn(expectedAuthResponseDTO);

        AuthResponseDTO resultAuthResponseDTO = authController.signUp(signUpRequest);

        assertEquals(expectedAuthResponseDTO, resultAuthResponseDTO);
        verify(authService, times(1)).signUp(signUpRequest);
    }

    @Test
    void signUp_ConflictExceptionThrown_ReturnsConflictStatus() throws UserConflictException {
        SignUpRequestDTO signUpRequest = new SignUpRequestDTO();

        when(authService.signUp(signUpRequest)).thenThrow(UserConflictException.class);

        assertThrows(UserConflictException.class, () -> authController.signUp(signUpRequest));
        verify(authService, times(1)).signUp(signUpRequest);
    }

    @Test
    void isGoodPassword_ValidPassword_ReturnsTrue() throws UserNotFoundException {
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setUser_id(1L);
        passwordDTO.setPassword("password123");

        when(authService.isGoodPassword(passwordDTO)).thenReturn(true);

        boolean result = authController.isgoodPassword(passwordDTO);

        assertTrue(result);

        verify(authService, times(1)).isGoodPassword(passwordDTO);
    }

    @Test
    void isGoodPassword_InvalidPassword_ReturnsFalse() throws UserNotFoundException {
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setUser_id(1L);
        passwordDTO.setPassword("incorrectPassword");

        when(authService.isGoodPassword(passwordDTO)).thenReturn(false);

        boolean result = authController.isgoodPassword(passwordDTO);

        assertFalse(result);

        verify(authService, times(1)).isGoodPassword(passwordDTO);
    }
}