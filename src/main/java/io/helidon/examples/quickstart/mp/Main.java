package io.helidon.examples.quickstart.mp;

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
import io.helidon.examples.quickstart.mp.producer.ConnectionCheckService;
import io.helidon.examples.quickstart.mp.repository.DynamicLinkRepository;
import io.helidon.microprofile.server.Server;

public final class Main {
    private static Logger logger;

    private static Config config;

    private Main() {
    }

    public static void main(final String[] args) {
        logger = LoggerFactory.getLogger(Main.class);
        config = Config.create();
        // Load Helidon config
        logger.debug("========== Helidon Configuration ==========");
        ConnectionCheckService.printConfigRecursive(config);

        Server server = startServer();

        logger.debug("========== Check database connection ==========");
        ConnectionCheckService.checkPostgresConnection(config);
        ConnectionCheckService.checkMongoConnection(config);

        System.out.println("SYSTEM.OUT Server started at: http://localhost:" + server.port() + "/greet");
        logger.info("/*************************************");
        logger.info("LOGGER Server started at: http://localhost:" + server.port());
        logger.info("*************************************/");

    }

    static Server startServer() {
        return Server.create().start();
    }



}