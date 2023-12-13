package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.PostgresDialect;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration
public class SpringDataJdbcConfiguration extends AbstractJdbcConfiguration {

    // эта конфигурация нужна, чтобы приложение стартовало, даже если база данных не запущена

    @Override
    public Dialect jdbcDialect(NamedParameterJdbcOperations operations) {
        return PostgresDialect.INSTANCE;
    }
}
