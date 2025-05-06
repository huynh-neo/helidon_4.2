package io.helidon.examples.quickstart.mp.cucumber;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class TestSetupHooks {

    @BeforeAll
    public static void beforeAll() {
        TestContainersEnvironment.startAll();
        HelidonTestServer.start();
    }

    @AfterAll
    public static void afterAll() {
        HelidonTestServer.stop();
        TestContainersEnvironment.stopAll();
    }
}
