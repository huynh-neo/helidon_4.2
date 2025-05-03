package io.helidon.examples.quickstart.mp.resource;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class AbstractContainerTest {
    static final PostgreSQLContainer<?> POSTGRES =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"))
                    .withDatabaseName("dynamiclinkdb")
                    .withUsername("appuser")
                    .withPassword("secret");

    static final MongoDBContainer MONGO =
            new MongoDBContainer(DockerImageName.parse("mongo:7.0"));

    @BeforeAll
    public static void startContainers() {
        System.out.println("////////////////////////////////////////////////////////////");
        if (!POSTGRES.isRunning()) {
            System.out.println("Start POSTGRES");
            POSTGRES.start();
        }
        if (!MONGO.isRunning()) {
            System.out.println("Start MONGO");
            MONGO.start();
        }

        // Set system properties or Helidon config override
        System.setProperty("javax.sql.DataSource.default.dataSource.url", POSTGRES.getJdbcUrl());
        System.setProperty("javax.sql.DataSource.default.dataSource.user", POSTGRES.getUsername());
        System.setProperty("javax.sql.DataSource.default.dataSource.password", POSTGRES.getPassword());

        System.setProperty("mongo.connection-string", MONGO.getConnectionString());
        System.setProperty("mongo.database", "analyticsdb");
    }
}
