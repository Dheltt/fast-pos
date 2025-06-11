package com.fast_pos.fast_pos.adapter.out.persistence.adapter;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.TenantStoresUsersEntity;
import com.fast_pos.fast_pos.adapter.out.persistence.jpa.JpaTenantStoresUsersRepository;
import com.fast_pos.fast_pos.domain.enums.UserRole;
import com.fast_pos.fast_pos.domain.port.out.TenantsUserManagementPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
@Repository
public class TenantsUserManagementAdapter implements TenantsUserManagementPort {
    private static final Logger logger = LoggerFactory.getLogger(TenantsUserManagementAdapter.class);

    private final JdbcTemplate jdbcTemplate;

    public TenantsUserManagementAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void registerUserToTenant(UUID userId, String name, String email, String password, UserRole role, String schemaName) {
        logger.info("Registering user to tenant");
        logger.debug("Input data -> userId: {}, name: {}, email: {}, role: {}, schemaName: {}", userId, name, email, role, schemaName);

        try {
            int rowsAffected = jdbcTemplate.update("""
                    INSERT INTO public.tenants_users (schema_name, name, email, password, role)
                    VALUES (?, ?, ?, ?, ?)
                """, schemaName, name, email, password, role.name());

            logger.info("Insert operation completed. Rows affected: {}", rowsAffected);
        } catch (Exception e) {
            logger.error("Error while registering user to tenant: {}", e.getMessage(), e);
            throw e; // optional: rethrow or handle as needed
        }
    }
}
