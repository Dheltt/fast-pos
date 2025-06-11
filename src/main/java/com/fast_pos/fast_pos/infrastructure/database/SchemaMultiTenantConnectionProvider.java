package com.fast_pos.fast_pos.infrastructure.database;

import org.hibernate.engine.jdbc.connections.internal.MultiTenantConnectionProviderInitiator;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class SchemaMultiTenantConnectionProvider implements MultiTenantConnectionProvider {
    private static final Logger log = LoggerFactory.getLogger(SchemaMultiTenantConnectionProvider.class);
    private final DataSource dataSource;

    public SchemaMultiTenantConnectionProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        log.debug("Getting any connection from the datasource");
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        log.debug("Releasing any connection");
        connection.close();
    }

    @Override
    public Connection getConnection(Object tenantIdentifier) throws SQLException {
        String tenantSchema = (String) tenantIdentifier;
        log.debug("Getting connection for tenant schema: {}", tenantSchema);
        Connection connection = getAnyConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute("SET search_path TO " + tenantSchema);
            log.debug("Set search_path to tenant schema: {}", tenantSchema);
        } catch (SQLException e) {
            log.error("Failed to set search_path to tenant: {}", tenantSchema, e);
            throw new SQLException("No se pudo cambiar el schema al tenant: " + tenantSchema, e);
        }
        return connection;
    }

    @Override
    public void releaseConnection(Object tenantIdentifier, Connection connection) throws SQLException {
        String tenantSchema = (String) tenantIdentifier;
        log.debug("Releasing connection and resetting search_path to public for tenant: {}", tenantIdentifier);
        try (Statement statement = connection.createStatement()) {
            if (!tenantSchema.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                throw new SQLException("Invalid schema name: " + tenantSchema);
            }
            statement.execute("SET search_path TO public");
        } finally {
            connection.close();
        }
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return unwrapType.isAssignableFrom(SchemaMultiTenantConnectionProvider.class)
                || unwrapType.isAssignableFrom(DataSource.class);
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        if (unwrapType.isAssignableFrom(SchemaMultiTenantConnectionProvider.class)) {
            return (T) this;
        } else if (unwrapType.isAssignableFrom(DataSource.class)) {
            return (T) dataSource;
        } else {
            return null;
        }
    }
}
