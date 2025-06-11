package com.fast_pos.fast_pos.application.service;

import com.fast_pos.fast_pos.domain.model.TenantStoresUsers;
import com.fast_pos.fast_pos.domain.port.in.StoreUseCase;

import java.util.Optional;
import java.util.UUID;

public class StoreService implements StoreUseCase {
    @Override
    public Optional<TenantStoresUsers> getTenantStoreById(UUID tenantId) {
        return Optional.empty();
    }

    @Override
    public Optional<TenantStoresUsers> getTenantStoreByName(String schemaName) {
        return Optional.empty();
    }
}
