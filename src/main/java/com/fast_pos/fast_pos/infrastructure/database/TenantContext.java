package com.fast_pos.fast_pos.infrastructure.database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TenantContext {
    private static final Logger log = LoggerFactory.getLogger(TenantContext.class);
    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setTenantSchema(String schema){
        log.debug("Setting tenant schema in context: {}", schema);
        currentTenant.set(schema);
    }

    public static String getTenantSchema(){
        String tenant = currentTenant.get();
        log.debug("Getting tenant schema from context: {}", tenant);
        return tenant;
    }

    public static void clear(){
        log.debug("Clearing tenant schema from context");
        currentTenant.remove();
    }
}
