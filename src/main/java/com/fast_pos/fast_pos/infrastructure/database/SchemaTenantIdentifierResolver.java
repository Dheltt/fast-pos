package com.fast_pos.fast_pos.infrastructure.database;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class SchemaTenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    private static final Logger log = LoggerFactory.getLogger(SchemaTenantIdentifierResolver.class);

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenant = TenantContext.getTenantSchema();
        log.debug("Resolving current tenant identifier: {}", tenant);
        return (tenant != null) ? tenant : "public";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
