package com.fast_pos.fast_pos.adapter.out.persistence.jpa;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.UserEntity;
import com.fast_pos.fast_pos.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
