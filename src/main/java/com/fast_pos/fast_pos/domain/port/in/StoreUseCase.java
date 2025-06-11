package com.fast_pos.fast_pos.domain.port.in;

import com.fast_pos.fast_pos.domain.model.TenantStoresUsers;

import java.util.Optional;
import java.util.UUID;

public interface StoreUseCase {
    Optional<TenantStoresUsers> getTenantStoreById(UUID tenantId);
    Optional<TenantStoresUsers> getTenantStoreByName(String schemaName);
}
