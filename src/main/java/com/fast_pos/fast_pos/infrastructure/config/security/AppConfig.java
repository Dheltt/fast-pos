package com.fast_pos.fast_pos.infrastructure.config.security;

import com.fast_pos.fast_pos.infrastructure.database.SchemaRoutingDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DataSource dataSource(DataSource realDataSource) {
        return new SchemaRoutingDataSource(realDataSource);
    }
}
