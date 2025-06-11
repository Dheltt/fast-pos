package com.fast_pos.fast_pos.domain.port.out;
import com.fast_pos.fast_pos.domain.model.Product;
import org.springframework.stereotype.Component;
import java.util.List;
 @Component
public interface TenantManagementRepositoryPort {
    void createSchemaForTenant(String schemaName);
    void createTablesForTenant(String schemaNme);
    void saveTenantInfo(String schemaName,String storeName,String ownerEmail);
    void createInitialOwnerUser(String schemaName,String name,String email,String password);
    void saveProducts(String schema, List<Product> products);
}
