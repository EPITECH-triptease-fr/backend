package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

class SignUpRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidSignUpRequestDTO() {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setUsername("testuser");
        signUpRequestDTO.setPassword("testpassword");
        signUpRequestDTO.setName("Test User");
        signUpRequestDTO.setEmail("test@example.com");

        var violations = validator.validate(signUpRequestDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidSignUpRequestDTO() {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();

        var violations = validator.validate(signUpRequestDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testGetterAndSetterMethods() {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setUsername("testuser");
        signUpRequestDTO.setPassword("testpassword");
        signUpRequestDTO.setName("Test User");
        signUpRequestDTO.setEmail("test@example.com");

        assertEquals("testuser", signUpRequestDTO.getUsername());
        assertEquals("testpassword", signUpRequestDTO.getPassword());
        assertEquals("Test User", signUpRequestDTO.getName());
        assertEquals("test@example.com", signUpRequestDTO.getEmail());
    }
}
