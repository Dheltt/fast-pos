package com.fast_pos.fast_pos.domain.port.out;

import com.fast_pos.fast_pos.domain.model.Product;
import com.fast_pos.fast_pos.domain.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
 @Component
public interface UserRepositoryPort {
    User save(User user);
    List<User> findAll();
    List<User> findByTenantStore(UUID tenantStore);
    void deleteById(UUID id);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
}
