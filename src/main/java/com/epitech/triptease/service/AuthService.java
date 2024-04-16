package com.epitech.triptease.service;

import com.epitech.triptease.entity.User;
import com.epitech.triptease.exception.user.UserConflictException;
import com.epitech.triptease.exception.user.UserNotFoundException;
import com.epitech.triptease.mapper.dto.AuthResponseDTO;
import com.epitech.triptease.mapper.dto.LoginRequestDTO;
import com.epitech.triptease.mapper.dto.PasswordDTO;
import com.epitech.triptease.mapper.dto.SignUpRequestDTO;
import com.epitech.triptease.security.SecurityConstants;
import com.epitech.triptease.security.TokenProvider;
import com.epitech.triptease.security.oauth2.OAuth2Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    public AuthResponseDTO login(LoginRequestDTO loginRequest){
        String token = authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO signUp(SignUpRequestDTO signUpRequest) throws UserConflictException {
        if (userService.hasUserWithUsername(signUpRequest.getUsername()) || userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new UserConflictException();
        }

        userService.saveUser(mapSignUpRequestToUser(signUpRequest));
        String token = authenticateAndGetToken(signUpRequest.getUsername(), signUpRequest.getPassword());
        return new AuthResponseDTO(token);
    }

    public  boolean isGoodPassword(PasswordDTO passwordDTO) throws UserNotFoundException {
        return passwordEncoder.matches(passwordDTO.getPassword(), userService.getUserById(passwordDTO.getUser_id()).getPassword());
    }

    private String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generateJwtToken(authentication);
    }

    private User mapSignUpRequestToUser(SignUpRequestDTO signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(SecurityConstants.ROLE_USER);
        user.setProvider(OAuth2Provider.LOCAL.toString());
        return user;
    }
}
