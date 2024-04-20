package com.epitech.triptease.service;

import com.epitech.triptease.entity.User;
import com.epitech.triptease.exception.user.UnauthorizedAccessException;
import com.epitech.triptease.exception.user.UserConflictException;
import com.epitech.triptease.exception.user.UserNotFoundException;
import com.epitech.triptease.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void createUser_ValidUser_ReturnsCreatedUser() throws UserConflictException {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);

        User createdUser = userService.createUser(user);

        assertEquals(user, createdUser);
        verify(userRepository, times(1)).existsByEmail(user.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void createUser_UserWithEmailAlreadyExists_ThrowsUserConflictException() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        assertThrows(UserConflictException.class, () -> userService.createUser(user));

        verify(userRepository, times(1)).existsByEmail(user.getEmail());
        verify(userRepository, never()).save(user);
    }

    @Test
    void getAllUser_NoUsers_ReturnsEmptyList() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        List<User> users = userService.getAllUser();

        assertNotNull(users);
        assertTrue(users.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_ExistingUserId_ReturnsUser() throws UserNotFoundException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserById(userId);

        assertEquals(user, retrievedUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserById_NonExistingUserId_ThrowsUserNotFoundException() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void hasUserWithUsername_ExistingUsername_ReturnsTrue() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userRepository.existsByUsername(username)).thenReturn(true);

        boolean result = userService.hasUserWithUsername(username);

        assertTrue(result);
        verify(userRepository, times(1)).existsByUsername(username);
    }

    @Test
    void hasUserWithUsername_NonExistingUsername_ReturnsFalse() {
        String username = "testuser";

        when(userRepository.existsByUsername(username)).thenReturn(false);

        boolean result = userService.hasUserWithUsername(username);

        assertFalse(result);
        verify(userRepository, times(1)).existsByUsername(username);
    }

    @Test
    void hasUserWithEmail_ExistingEmail_ReturnsTrue() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userRepository.existsByEmail(email)).thenReturn(true);

        boolean result = userService.hasUserWithEmail(email);

        assertTrue(result);
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void hasUserWithEmail_NonExistingEmail_ReturnsFalse() {
        String email = "test@example.com";

        when(userRepository.existsByEmail(email)).thenReturn(false);

        boolean result = userService.hasUserWithEmail(email);

        assertFalse(result);
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void saveUser_ValidUser_ReturnsSavedUser() {
        User user = new User();

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser_ValidUser_DeletesUser() {
        User user = new User();

        userService.deleteUser(user);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void updateUserProfile_ValidUser_ReturnsUpdatedUser() throws UserNotFoundException, UnauthorizedAccessException {
        User user = new User();
        user.setId(1L);
        user.setName("toto");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("tata");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.updateUserProfile(1L, updatedUser);

        assertNotNull(result);
        assertEquals(result.getId(), user.getId());
        assertEquals(result.getName(), updatedUser.getName());

        verify(userRepository, times(1)).save(updatedUser);

    }

    @Test
    void updateUserProfile_InvalidUser_ThrowsUnauthorizedAccessException() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User updatedUser = new User();
        updatedUser.setId(2L); // Different ID

        assertThrows(UnauthorizedAccessException.class, () -> userService.updateUserProfile(userId, updatedUser));

        verify(userRepository, never()).save(any());
    }

    @Test
    void getUserProviderById_ExistingUserId_ReturnsProvider() throws UserNotFoundException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setProvider("Google");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        String provider = userService.getUserProviderById(userId);

        assertEquals(user.getProvider(), provider);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserProviderById_NonExistingUserId_ThrowsUserNotFoundException() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserProviderById(userId));

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void updateUserProfileAdmin_ValidUser_ReturnsUpdatedUser() throws UserNotFoundException {
        User user = new User();
        user.setId(1L);
        user.setName("toto");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("tata");

        when(userRepository.existsById(user.getId())).thenReturn(true);

        User result = userService.updateUserProfileAdmin(updatedUser);

        assertNotNull(result);
        assertEquals(result.getId(), user.getId());
        assertEquals(result.getName(), updatedUser.getName());

        verify(userRepository, times(1)).existsById(user.getId());
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    void updateUserProfileAdmin_InvalidUser_ThrowsUserNotFoundException() {
        Long userId = 1L;
        User updatedUser = new User();
        updatedUser.setId(userId);

        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.updateUserProfileAdmin(updatedUser));

        verify(userRepository, never()).save(any());
    }

    @Test
    void updateUser_InvalidUser_ThrowsUserNotFoundException() {
        Long userId = 1L;
        User updatedUser = new User();
        updatedUser.setId(userId);

        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.updateUserProfile(1L,updatedUser));

        verify(userRepository, never()).save(any());
    }

    @Test
    void testDeleteUserById() {
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(true);

        assertDoesNotThrow(() -> userService.deleteUserById(userId));

        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
