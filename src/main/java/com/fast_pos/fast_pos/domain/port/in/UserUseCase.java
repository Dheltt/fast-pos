package com.fast_pos.fast_pos.domain.port.in;

import com.fast_pos.fast_pos.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserUseCase {
    User createUser(User user);
    Optional<User> getUserById(UUID id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    void deleteUser(UUID id);
}
