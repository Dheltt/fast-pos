package com.fast_pos.fast_pos.adapter.out.persistence.jpa;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.TenantStoresUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface JpaTenantStoresUsersRepository extends JpaRepository<TenantStoresUsersEntity, UUID> {
    Optional<TenantStoresUsersEntity> findByEmail(String email);
}
