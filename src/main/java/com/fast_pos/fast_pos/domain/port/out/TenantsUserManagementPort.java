package com.fast_pos.fast_pos.domain.port.out;

import com.fast_pos.fast_pos.domain.enums.UserRole;
import org.springframework.stereotype.Component;


import java.util.UUID;

@Component
public interface TenantsUserManagementPort {
    void registerUserToTenant(UUID userId,String name, String email, String password, UserRole role,String schemaName);
}
