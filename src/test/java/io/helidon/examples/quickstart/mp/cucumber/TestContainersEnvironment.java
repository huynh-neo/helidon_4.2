package io.helidon.examples.quickstart.mp.cucumber;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

public class TestContainersEnvironment {

    public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("dynamiclinkdb")
            .withUsername("appuser")
            .withPassword("secret")
            .withInitScript("db/init_posgres.sql");

    public static final GenericContainer<?> mongo = new GenericContainer<>(DockerImageName.parse("mongo:6.0"))
            .withExposedPorts(27017)
            .withEnv("MONGO_INITDB_DATABASE", "analyticsdb")
            .withEnv("MONGO_INITDB_ROOT_USERNAME", "mongoadmin")
            .withEnv("MONGO_INITDB_ROOT_PASSWORD", "secret")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("mongo/init-mongo.js"),
                    "/docker-entrypoint-initdb.d/init.js"
            );

    public static void startAll() {
        postgres.start();
        mongo.start();

        System.setProperty("db.url", postgres.getJdbcUrl());
        System.setProperty("db.user", postgres.getUsername());
        System.setProperty("db.password", postgres.getPassword());

        String mongoHost = mongo.getHost();
        Integer mongoPort = mongo.getMappedPort(27017);
        String mongoUri = String.format("mongodb://mongoadmin:secret@%s:%d", mongoHost, mongoPort);

        System.setProperty("mongo.connection-string", mongoUri);
        System.setProperty("mongo.uri", mongoUri);
        System.setProperty("mongo.user", "mongoadmin");
        System.setProperty("mongo.password", "secret");
        System.setProperty("mongo.database", "analyticsdb");
    }

    public static void stopAll() {
        postgres.stop();
        mongo.stop();
    }
}
