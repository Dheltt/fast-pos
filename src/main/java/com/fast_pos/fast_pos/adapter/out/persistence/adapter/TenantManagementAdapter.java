package com.fast_pos.fast_pos.adapter.out.persistence.adapter;

import com.fast_pos.fast_pos.adapter.in.dto.response.ProductCsvDTO;
import com.fast_pos.fast_pos.domain.model.Product;
import com.fast_pos.fast_pos.domain.port.out.TenantManagementRepositoryPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Repository
public class TenantManagementAdapter implements TenantManagementRepositoryPort {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(TenantManagementAdapter.class);

    public TenantManagementAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void createSchemaForTenant(String schemaName){
        validateSchemaName(schemaName);
        try {
            jdbcTemplate.execute("CREATE SCHEMA \"" + schemaName + "\"");
            log.info("Esquema '{}' creado correctamente", schemaName);
        } catch (Exception e) {
            log.error("Error al crear esquema '{}': {}", schemaName, e.getMessage());
            throw e;
        }
    }
    private void validateSchemaName(String schemaName) {
        if (!schemaName.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Nombre de esquema inválido");
        }
    }
    @Override
    public void createTablesForTenant(String schemaName) {
        jdbcTemplate.execute("""
            CREATE TABLE %s.users (
                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                store_name VARCHAR(100) NOT NULL,
                name VARCHAR(100),
                email VARCHAR(100),
                password VARCHAR(255),
                role VARCHAR(50)
            );
        """.formatted(schemaName));

        jdbcTemplate.execute("""
            CREATE TABLE %s.products (
                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                name VARCHAR(100),
                price DECIMAL(10,2),
                stock INT,
                category VARCHAR(100)
            );
        """.formatted(schemaName));

        jdbcTemplate.execute("""
            CREATE TABLE %s.sales (
                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                timestamp TIMESTAMP NOT NULL,
                total_amount DECIMAL(10,2) NOT NULL,
                payment_method VARCHAR(50) NOT NULL,
                user_id UUID NOT NULL
            );
        """.formatted(schemaName));
        jdbcTemplate.execute("""
        CREATE TABLE %s.sale_products (
            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            sale_id UUID NOT NULL REFERENCES %s.sales(id) ON DELETE CASCADE,
            product_id UUID NOT NULL REFERENCES %s.products(id),
            product_name VARCHAR(100),
            quantity INT NOT NULL,
            price DECIMAL(10,2) NOT NULL
        );
    """.formatted(schemaName, schemaName, schemaName));
        log.info("Tablas creadas correctamente en el esquema '{}'", schemaName);
    }

    @Override
    public void saveTenantInfo(String schemaName, String storeName, String ownerEmail) {
        jdbcTemplate.update("""
            INSERT INTO tenant_registry (schema_name, store_name, owner_email)
            VALUES (?, ?, ?)
        """, schemaName, storeName, ownerEmail);
        log.info("Se insertó información del tenant: esquema={}, tienda={}, email={}",
                schemaName, storeName, ownerEmail);
    }

    @Override
    public void createInitialOwnerUser(String schemaName, String name, String email, String encodedPassword) {
        jdbcTemplate.update("""
            INSERT INTO %s.users (store_name,name, email, password, role)
            VALUES (?,?, ?, ?, ?)
        """.formatted(schemaName), schemaName, name, email, encodedPassword, "OWNER");

        try {
            jdbcTemplate.update("""
            INSERT INTO tenants_users (schema_name, name, email, password, role)
            VALUES (?, ?, ?, ?, ?)
        """, schemaName, name, email, encodedPassword, "OWNER");
        } catch (Exception e) {
            log.error("Error al insertar usuario en tenant_users: {}", e.getMessage(), e);
            throw e;
        }
    }



    @Override
    public void saveProducts(String schemaName, List<Product> products) {
        String sql = """
            INSERT INTO %s.products (name, price, stock, category)
            VALUES (?, ?, ?, ?)
        """.formatted(schemaName);

        for (Product product : products) {
            try {
                int rows = jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getStock(), product.getCategory());
                log.debug("Producto insertado en '{}': nombre={}, precio={}, stock={}, categoría={}, filas afectadas={}",
                        schemaName, product.getName(), product.getPrice(), product.getStock(), product.getCategory(), rows);
            } catch (Exception e) {
                log.error("Error al insertar producto en '{}': nombre={}, error={}", schemaName, product.getName(), e.getMessage());
            }
        }
        log.info("Todos los productos han sido procesados para el esquema '{}'", schemaName);
    }

    private String hash(String raw) {
        return BCrypt.hashpw(raw, BCrypt.gensalt());
    }

}
