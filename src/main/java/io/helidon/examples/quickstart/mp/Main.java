package io.helidon.examples.quickstart.mp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.helidon.microprofile.server.Server;

public final class Main {
    private static Logger logger;
    private Main() {
    }

    public static void main(final String[] args) {
        logger = LoggerFactory.getLogger(Main.class);

        Server server = startServer();
        System.out.println("Server started at: http://localhost:" + server.port() + "/greet");
        logger.info("/*************************************");
        logger.info("LOGGER Server started at: http://localhost:" + server.port());
        logger.info("*************************************/");
        // Logger LOGGER = LoggerFactory.getLogger(Main.class);
        // LOGGER.info("LOGGER Server started at: http://localhost:" + server.port());
        System.out.println("Server main finished");
    }

    static Server startServer() {
        return Server.create().start();
    }
}