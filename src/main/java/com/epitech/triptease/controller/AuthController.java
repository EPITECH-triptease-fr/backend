package com.epitech.triptease.controller;

import com.epitech.triptease.exception.user.UserConflictException;
import com.epitech.triptease.exception.user.UserNotFoundException;
import com.epitech.triptease.mapper.dto.AuthResponseDTO;
import com.epitech.triptease.mapper.dto.LoginRequestDTO;
import com.epitech.triptease.mapper.dto.PasswordDTO;
import com.epitech.triptease.mapper.dto.SignUpRequestDTO;
import com.epitech.triptease.security.SecurityConstants;
import com.epitech.triptease.service.AuthService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public AuthResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return authService.login(loginRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponseDTO signUp(@Valid @RequestBody SignUpRequestDTO signUpRequest) throws UserConflictException {
        return authService.signUp(signUpRequest);
    }

    @PostMapping("/verif")
    @RolesAllowed({SecurityConstants.ROLE_USER, SecurityConstants.ROLE_ADMIN})
    public boolean isgoodPassword (@Valid @RequestBody PasswordDTO passwordDTO) throws UserNotFoundException {
        return authService.isGoodPassword(passwordDTO);
    }
}
