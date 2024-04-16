package com.epitech.triptease.controller;

import com.epitech.triptease.entity.User;
import com.epitech.triptease.exception.user.UnauthorizedAccessException;
import com.epitech.triptease.exception.user.UserConflictException;
import com.epitech.triptease.exception.user.UserNotFoundException;
import com.epitech.triptease.mapper.UserMapper;
import com.epitech.triptease.mapper.dto.UserDTO;
import com.epitech.triptease.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService, userMapper);
    }

    @Test
    void getUserById_ValidUserId_ReturnsUserDTO() throws UserNotFoundException {
        Long userId = 1L;
        UserDTO expectedUserDTO = new UserDTO();

        when(userService.getUserById(userId)).thenReturn(new User());
        when(userMapper.userEntityToUserDto(any(User.class))).thenReturn(expectedUserDTO);

        UserDTO resultUserDTO = userController.getUserById(userId);

        assertEquals(expectedUserDTO, resultUserDTO);
        verify(userService, times(1)).getUserById(userId);
        verify(userMapper, times(1)).userEntityToUserDto(any(User.class));
    }

    @Test
    void getUserById_InvalidUserId_ThrowsUserNotFoundException() throws UserNotFoundException {
        Long userId = 1L;

        when(userService.getUserById(userId)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userController.getUserById(userId));

        verify(userService, times(1)).getUserById(userId);
        verify(userMapper, never()).userEntityToUserDto(any(User.class));
    }

    @Test
    void getAllUser_ReturnsListOfUserDTOs() {
        List<UserDTO> expectedUserDTOs = Arrays.asList(new UserDTO(), new UserDTO());

        when(userService.getAllUser()).thenReturn(Arrays.asList(new User(), new User()));
        when(userMapper.userEntityListToUserListDto(anyList())).thenReturn(expectedUserDTOs);

        List<UserDTO> resultUserDTOs = userController.getAllUser();

        assertEquals(expectedUserDTOs, resultUserDTOs);
        verify(userService, times(1)).getAllUser();
        verify(userMapper, times(1)).userEntityListToUserListDto(anyList());
    }

    @Test
    void createUser_ValidUserDTO_ReturnsCreatedUserDTO() throws UserConflictException {
        UserDTO userDTO = new UserDTO();
        UserDTO expectedUserDTO = new UserDTO();

        when(userMapper.userDtoToUserEntity(userDTO)).thenReturn(new User());
        when(userService.createUser(any(User.class))).thenReturn(new User());
        when(userMapper.userEntityToUserDto(any(User.class))).thenReturn(expectedUserDTO);

        UserDTO resultUserDTO = userController.createUser(userDTO);

        assertEquals(expectedUserDTO, resultUserDTO);
        verify(userMapper, times(1)).userDtoToUserEntity(userDTO);
        verify(userService, times(1)).createUser(any(User.class));
        verify(userMapper, times(1)).userEntityToUserDto(any(User.class));
    }

    @Test
    void createUser_ConflictExceptionThrown_ThrowsConflictException() throws UserConflictException {
        UserDTO userDTO = new UserDTO();

        when(userMapper.userDtoToUserEntity(userDTO)).thenReturn(new User());
        when(userService.createUser(any(User.class))).thenThrow(UserConflictException.class);

        assertThrows(UserConflictException.class, () -> userController.createUser(userDTO));

        verify(userMapper, times(1)).userDtoToUserEntity(userDTO);
        verify(userService, times(1)).createUser(any(User.class));
        verify(userMapper, never()).userEntityToUserDto(any(User.class));
    }

    @Test
    void updateUserProfile_ValidUserIdAndUserDTO_ReturnsUpdatedUserDTO() throws UserNotFoundException, UnauthorizedAccessException {
        Long userId = 1L;
        UserDTO updatedUserDTO = new UserDTO();
        UserDTO expectedUserDTO = new UserDTO();

        when(userMapper.userDtoToUserEntity(updatedUserDTO)).thenReturn(new User());
        when(userService.updateUserProfile(eq(userId), any(User.class))).thenReturn(new User());
        when(userMapper.userEntityToUserDto(any(User.class))).thenReturn(expectedUserDTO);

        UserDTO resultUserDTO = userController.updateUserProfile(userId, updatedUserDTO);

        assertEquals(expectedUserDTO, resultUserDTO);
        verify(userMapper, times(1)).userDtoToUserEntity(updatedUserDTO);
        verify(userService, times(1)).updateUserProfile(eq(userId), any(User.class));
        verify(userMapper, times(1)).userEntityToUserDto(any(User.class));
    }


    @Test
    void updateUserProfileAdmin_ValidUserDTO_ReturnsUpdatedUserDTO() throws UserNotFoundException {
        UserDTO updatedUserDTO = new UserDTO();
        UserDTO expectedUserDTO = new UserDTO();

        when(userMapper.userDtoToUserEntity(updatedUserDTO)).thenReturn(new User());
        when(userService.updateUserProfileAdmin(any(User.class))).thenReturn(new User());
        when(userMapper.userEntityToUserDto(any(User.class))).thenReturn(expectedUserDTO);

        UserDTO resultUserDTO = userController.updateUserProfileAdmin(updatedUserDTO);

        assertEquals(expectedUserDTO, resultUserDTO);
        verify(userMapper, times(1)).userDtoToUserEntity(updatedUserDTO);
        verify(userService, times(1)).updateUserProfileAdmin(any(User.class));
        verify(userMapper, times(1)).userEntityToUserDto(any(User.class));
    }

    @Test
    void getUserProviderById_ValidUserId_ReturnsProvider() throws UserNotFoundException {
        Long userId = 1L;
        String expectedProvider = "SomeProvider";

        when(userService.getUserProviderById(userId)).thenReturn(expectedProvider);

        String resultProvider = userController.getUserProviderById(userId);

        assertEquals(expectedProvider, resultProvider);
        verify(userService, times(1)).getUserProviderById(userId);
    }

    @Test
    void testDeleteUserById() throws UserNotFoundException {
        Long userId = 1L;

        doNothing().when(userService).deleteUserById(userId);

        assertDoesNotThrow(() -> userController.deleteUserById(userId));

        verify(userService, times(1)).deleteUserById(userId);
    }
}
