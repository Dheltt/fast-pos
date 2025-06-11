package com.fast_pos.fast_pos.adapter.out.persistence.mapper;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.TenantStoresUsersEntity;
import com.fast_pos.fast_pos.domain.model.TenantStoresUsers;
import org.springframework.stereotype.Component;

@Component
public class TenantStoresUsersMapper {
    public  TenantStoresUsers toDomain(TenantStoresUsersEntity entity) {
        return new TenantStoresUsers(
                entity.getId(),
                entity.getSchemaName(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getUserRole()
        );
    }
}
