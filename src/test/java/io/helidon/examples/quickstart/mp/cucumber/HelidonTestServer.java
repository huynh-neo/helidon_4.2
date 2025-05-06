package io.helidon.examples.quickstart.mp.cucumber;

import io.helidon.microprofile.server.Server;
import io.restassured.RestAssured;

public class HelidonTestServer {

    private static Server server;

    public static void start() {
        if (server == null) {
            server = Server.create().start();
            RestAssured.port = server.port();
        }
    }

    public static void stop() {
        if (server != null) {
            server.stop();
            server = null;
        }
    }
}
