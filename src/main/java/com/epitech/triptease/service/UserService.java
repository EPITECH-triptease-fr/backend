package com.epitech.triptease.service;

import com.epitech.triptease.entity.User;
import com.epitech.triptease.exception.user.UnauthorizedAccessException;
import com.epitech.triptease.exception.user.UserConflictException;
import com.epitech.triptease.exception.user.UserNotFoundException;
import com.epitech.triptease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) throws UserConflictException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserConflictException();
        }

        userRepository.save(user);
        return user;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        return userOptional.get();
    }

    public User updateUserProfile(Long userId, User updatedUser) throws UserNotFoundException, UnauthorizedAccessException {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        if (!Objects.equals(updatedUser.getId(), userId)) {
            throw new UnauthorizedAccessException();
        }

        User user = optionalUser.get();

        updatedUser.setId(userId);
        updatedUser.setRole(user.getRole());
        updatedUser.setProvider(user.getProvider());

        userRepository.save(updatedUser);

        return updatedUser;
    }

    public User updateUserProfileAdmin(User updatedUser) throws UserNotFoundException {
        if (!userRepository.existsById(updatedUser.getId())) {
            throw new UserNotFoundException();
        }

        userRepository.save(updatedUser);

        return updatedUser;
    }

    public String getUserProviderById(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        return userOptional.get().getProvider();
    }

    public void deleteUserById(Long userId) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        userRepository.deleteById(userId);
    }


    public boolean hasUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
