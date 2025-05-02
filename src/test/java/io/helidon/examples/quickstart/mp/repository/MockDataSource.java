package io.helidon.examples.quickstart.mp.repository;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

@ApplicationScoped
public class MockDataSource {

    @Produces
    @Alternative
    @Priority(1)
    public DataSource produce() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(System.getProperty("PG_JDBC_URL"));
        config.setUsername(System.getProperty("PG_USER"));
        config.setPassword(System.getProperty("PG_PASS"));
        return new HikariDataSource(config);
    }
}
