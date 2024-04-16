package com.epitech.triptease.mapper;

import com.epitech.triptease.entity.User;
import com.epitech.triptease.mapper.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserMapperTest {

    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userMapper = Mappers.getMapper(UserMapper.class);
        userMapper.encoder = passwordEncoder; // Inject the mocked PasswordEncoder
    }

    @Test
    void testUserDtoToUserEntity() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("sampleUser");
        userDTO.setPassword("password123");

        when(passwordEncoder.encode("password123")).thenReturn("encryptedPassword");

        User userEntity = userMapper.userDtoToUserEntity(userDTO);

        assertEquals(userDTO.getId(), userEntity.getId());
        assertEquals(userDTO.getUsername(), userEntity.getUsername());
        assertEquals("encryptedPassword", userEntity.getPassword());
    }

    @Test
    void testUserEntityToUserDto() {
        User userEntity = new User();
        userEntity.setId(2L);
        userEntity.setUsername("anotherUser");
        userEntity.setPassword("encryptedPassword");

        when(passwordEncoder.encode("password123")).thenReturn("encryptedPassword");

        UserDTO userDTO = userMapper.userEntityToUserDto(userEntity);

        assertEquals(userEntity.getId(), userDTO.getId());
        assertEquals(userEntity.getUsername(), userDTO.getUsername());
        assertEquals("encryptedPassword", userDTO.getPassword());
    }
}