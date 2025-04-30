package io.helidon.examples.quickstart.mp.producer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.helidon.config.Config;

public class ConnectionCheckService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionCheckService.class);

    public static void checkPostgresConnection(Config config) {
        Config dsConfig = config.get("javax.sql.DataSource.default");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDataSourceClassName(dsConfig.get("dataSourceClassName").asString().get());
        hikariConfig.addDataSourceProperty("url", dsConfig.get("dataSource.url").asString().get());
        hikariConfig.addDataSourceProperty("user", dsConfig.get("dataSource.user").asString().get());
        hikariConfig.addDataSourceProperty("password", dsConfig.get("dataSource.password").asString().get());

        hikariConfig.setMaximumPoolSize(dsConfig.get("maximumPoolSize").asInt().orElse(10));
        hikariConfig.setMinimumIdle(dsConfig.get("minimumIdle").asInt().orElse(2));

        try (HikariDataSource ds = new HikariDataSource(hikariConfig);
                Connection conn = ds.getConnection()) {
            if (!conn.isClosed()) {
                LOGGER.info("✅ PostgreSQL connection successful.");
            }
        } catch (SQLException e) {
            LOGGER.error("❌ PostgreSQL connection failed: " + e);
            System.exit(1);
        }
    }

    public static void checkMongoConnection(Config config) {
        String uri = config.get("mongo.connection-string").asString().orElseThrow();
        String dbName = config.get("mongo.database").asString().orElse("analyticsdb");

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);
            db.listCollectionNames().first(); // ping by accessing collection names
            LOGGER.info("✅ MongoDB connection to " + dbName + " successful.");
        } catch (Exception e) {
            LOGGER.error("❌ MongoDB connection failed: " + e);
            System.exit(1);
        }
    }

    public static void printConfigRecursive(Config config) {
        Map<String, String> flatConfig = new TreeMap<>();

        // Flatten the config tree
        config.traverse()
                .forEach(node -> {
                    if (node.hasValue()) {
                        flatConfig.put(node.key().toString(), node.asString().get());
                    }
                });

        // Print sorted keys
        flatConfig.forEach((key, value) -> {
            LOGGER.info(key + " = " + value);
        });
    }
}
