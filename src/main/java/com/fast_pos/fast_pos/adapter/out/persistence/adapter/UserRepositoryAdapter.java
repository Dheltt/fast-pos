package com.fast_pos.fast_pos.adapter.out.persistence.adapter;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.UserEntity;
import com.fast_pos.fast_pos.adapter.out.persistence.jpa.JpaUserRepository;
import com.fast_pos.fast_pos.adapter.out.persistence.mapper.UserMapper;
import com.fast_pos.fast_pos.domain.model.User;
import com.fast_pos.fast_pos.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final UserMapper userMapper;
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository,UserMapper userMapper){
        this.jpaUserRepository=jpaUserRepository;
        this.userMapper=userMapper;
    }
    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(entity);
        return  userMapper.toDomain(savedEntity);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll()
                .stream()
                .map(userMapper::toDomain)
                .toList();
    }

    @Override
    public List<User> findByTenantStore(UUID tenantStore) {
        return List.of();
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaUserRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }
}
