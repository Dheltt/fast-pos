package com.fast_pos.fast_pos.application.service;

import com.fast_pos.fast_pos.application.exceptions.UserNotFoundException;
import com.fast_pos.fast_pos.domain.enums.UserRole;
import com.fast_pos.fast_pos.domain.model.User;
import com.fast_pos.fast_pos.domain.port.in.UserUseCase;
import com.fast_pos.fast_pos.domain.port.out.TenantsUserManagementPort;
import com.fast_pos.fast_pos.domain.port.out.UserRepositoryPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserService implements UserUseCase{
    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryPort userRepositoryPort;
    private final TenantsUserManagementPort tenantsUserManagementPort;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public UserService(UserRepositoryPort userRepositoryPort,
                       PasswordEncoder passwordEncoder,
                       TenantsUserManagementPort tenantsUserManagementPort){
        this.userRepositoryPort=userRepositoryPort;
        this.passwordEncoder= passwordEncoder;
        this.tenantsUserManagementPort=tenantsUserManagementPort;
    }

    @Override
    public User createUser(User user) {
        user.changePassword(passwordEncoder.encode(user.getPassword()));
        logger.info("Creating the user with email: {}", user.getEmail());
        User savedUser = userRepositoryPort.save(user);// se guarda primero
        String schemaName = user.getTenantStoreName();

        //save in tenants_users
        tenantsUserManagementPort.registerUserToTenant(savedUser.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                UserRole.CASHIER,
                user.getTenantStoreName());
        logger.info("User saved successfully: {}", savedUser.getId());  // ahora sí se loguea
        return savedUser;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepositoryPort.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }

    @Override
    public void deleteUser(UUID userId) {
       if(!userRepositoryPort.findById(userId).isPresent()){
           throw new UserNotFoundException(userId);
       }
       userRepositoryPort.deleteById(userId);
    }
}
