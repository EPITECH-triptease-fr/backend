package com.epitech.triptease.controller;

import com.epitech.triptease.exception.user.UnauthorizedAccessException;
import com.epitech.triptease.exception.user.UserConflictException;
import com.epitech.triptease.exception.user.UserNotFoundException;
import com.epitech.triptease.mapper.UserMapper;
import com.epitech.triptease.mapper.dto.UserDTO;
import com.epitech.triptease.security.SecurityConstants;
import com.epitech.triptease.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/{userId}")
    @RolesAllowed({SecurityConstants.ROLE_USER, SecurityConstants.ROLE_ADMIN})
    public UserDTO getUserById(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
        return  userMapper.userEntityToUserDto(userService.getUserById(userId));
    }

    @GetMapping()
    @RolesAllowed(SecurityConstants.ROLE_ADMIN)
    public List<UserDTO> getAllUser() {
        return userMapper.userEntityListToUserListDto(userService.getAllUser());
    }

    @PostMapping()
    @RolesAllowed(SecurityConstants.ROLE_ADMIN)
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) throws UserConflictException {
        return userMapper.userEntityToUserDto(userService.createUser(userMapper.userDtoToUserEntity(userDTO)));
    }

    @PutMapping("/{userId}")
    @RolesAllowed({SecurityConstants.ROLE_USER, SecurityConstants.ROLE_ADMIN})
    public UserDTO updateUserProfile (@PathVariable(value = "userId")Long userId, @Valid @RequestBody UserDTO updatedUser) throws UserNotFoundException, UnauthorizedAccessException {
        return userMapper.userEntityToUserDto(userService.updateUserProfile(userId,userMapper.userDtoToUserEntity(updatedUser)));
    }

    @PutMapping
    @RolesAllowed({SecurityConstants.ROLE_ADMIN})
    public UserDTO updateUserProfileAdmin(@Valid @RequestBody UserDTO updatedUser) throws UserNotFoundException {
        return userMapper.userEntityToUserDto(userService.updateUserProfileAdmin(userMapper.userDtoToUserEntity(updatedUser)));
    }

    @GetMapping("/{userId}/provider")
    @RolesAllowed({SecurityConstants.ROLE_ADMIN})
    public String getUserProviderById(@PathVariable(value = "userId")Long userId) throws UserNotFoundException {
        return userService.getUserProviderById(userId);
    }

    @DeleteMapping("/{userId}")
    @RolesAllowed({SecurityConstants.ROLE_ADMIN})
    public void deleteUserById(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
    }
}
