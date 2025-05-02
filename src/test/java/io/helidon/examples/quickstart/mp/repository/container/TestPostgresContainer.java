package io.helidon.examples.quickstart.mp.repository.container;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestPostgresContainer extends PostgreSQLContainer<TestPostgresContainer> {
    private static final TestPostgresContainer INSTANCE = new TestPostgresContainer();

    private TestPostgresContainer() {
        super("postgres:15");
        withDatabaseName("testdb");
        withUsername("testuser");
        withPassword("testpass");
        withInitScript("init.sql");
    }

    public static TestPostgresContainer getInstance() {
        return INSTANCE;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("PG_JDBC_URL", getJdbcUrl());
        System.setProperty("PG_USER", getUsername());
        System.setProperty("PG_PASS", getPassword());
    }

    @Override
    public void stop() {
        // Do nothing — JVM shutdown hook will clean up
    }
}
