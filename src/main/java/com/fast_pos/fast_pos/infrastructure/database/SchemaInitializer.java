package com.fast_pos.fast_pos.infrastructure.database;

import com.fast_pos.fast_pos.domain.model.Product;
import com.fast_pos.fast_pos.domain.model.User;
import com.fast_pos.fast_pos.application.exceptions.DatabaseOperationException;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

@Component
public class SchemaInitializer {
    private static final Logger log = LoggerFactory.getLogger(SchemaInitializer.class);

    public void init(String dbName, User ownerUser, List<Product> products) {
        String url = "jdbc:postgresql://localhost:5432/" + dbName;
        String user = "postgres";
        String password = "12345678";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            log.info("Inicializando esquema para base de datos '{}'", dbName);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                    store_name VARCHAR(100) NOT NULL,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL,
                    role VARCHAR(50) NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS products (
                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                    name VARCHAR(255) NOT NULL,
                    price DECIMAL(10, 2) NOT NULL,
                    stock INTEGER NOT NULL,
                    category VARCHAR(100)
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS sales (
                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                    product_id UUID NOT NULL REFERENCES products(id),
                    user_id UUID NOT NULL REFERENCES users(id),
                    quantity INTEGER NOT NULL,
                    total DECIMAL(10, 2) NOT NULL,
                    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            log.info("Insertando usuario propietario...");
            String insertUserSql = "INSERT INTO users (id, name, email, password, rol) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertUserSql)) {
                ps.setObject(1, ownerUser.getId());
                ps.setString(2, ownerUser.getName());
                ps.setString(3, ownerUser.getEmail());
                ps.setString(4, ownerUser.getPassword());
                ps.setString(5, ownerUser.getRole().toString());
                ps.executeUpdate();
            }

            log.info("Insertando productos...");
            String insertProductSql = "INSERT INTO products (id, name, price, stock, category) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertProductSql)) {
                for (Product product : products) {
                    log.debug("Insertando producto: id={}, name={}, price={}, stock={}, category={}",
                            product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getCategory());
                    try {
                        ps.setObject(1, product.getId());
                        ps.setString(2, product.getName());
                        ps.setBigDecimal(3, product.getPrice());
                        ps.setInt(4, product.getStock());
                        ps.setString(5, product.getCategory());
                        ps.addBatch();
                    } catch (SQLException ex) {
                        log.error("Error al preparar inserción del producto con id {}: {}", product.getId(), ex.getMessage());
                    }
                }
                ps.executeBatch();
                log.info("Todos los productos fueron insertados.");
            }

            log.info("Esquema inicializado correctamente para '{}'", dbName);

        } catch (SQLException e) {
            log.error("Error al inicializar el esquema para '{}'", dbName, e);
            throw new DatabaseOperationException("Error while initializing the schema for database: " + dbName, e);
        }
    }
}