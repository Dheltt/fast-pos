package com.fast_pos.fast_pos.infrastructure.database;
import org.springframework.jdbc.datasource.AbstractDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SchemaRoutingDataSource extends AbstractDataSource {

    private static final Logger log = LoggerFactory.getLogger(SchemaRoutingDataSource.class);
    private final DataSource dataSource;

    public SchemaRoutingDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        String schema = TenantContext.getTenantSchema();
        log.debug("SchemaRoutingDataSource.getConnection() called, tenant schema: {}", schema);
        if (schema != null && !schema.isEmpty()) {
            connection.setSchema(schema);
            log.debug("Set connection schema to {}", schema);
        }
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection();
    }
}