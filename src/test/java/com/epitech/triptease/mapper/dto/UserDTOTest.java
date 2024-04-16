package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

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
    void testValidUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        userDTO.setPassword("testpassword");
        userDTO.setName("Test User");
        userDTO.setEmail("test@example.com");

        // Validate the userDTO object
        var violations = validator.validate(userDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testGetterAndSetterMethods() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        userDTO.setId(1L);
        userDTO.setRole("bonsoirnon");
        userDTO.setProvider("ahah");
        userDTO.setPassword("testpassword");
        userDTO.setName("Test User");
        userDTO.setEmail("test@example.com");

        assertEquals("testuser", userDTO.getUsername());
        assertEquals("testpassword", userDTO.getPassword());
        assertEquals("Test User", userDTO.getName());
        assertEquals("test@example.com", userDTO.getEmail());
        assertEquals(1L, userDTO.getId());
        assertEquals("ahah", userDTO.getProvider());
        assertEquals("bonsoirnon", userDTO.getRole());
    }
}
