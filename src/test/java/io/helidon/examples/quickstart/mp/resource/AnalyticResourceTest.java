package io.helidon.examples.quickstart.mp.resource;

import io.helidon.microprofile.testing.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.client.WebTarget;
import static org.junit.jupiter.api.Assertions.*;

@HelidonTest
public class AnalyticResourceTest extends AbstractContainerTest {

    @Inject
    private WebTarget target;

    @Test
    void testInsertAndFetchAnalytics() {
        JsonObject request = Json.createObjectBuilder()
                .add("linkId", "12345")
                .add("timestamp", "2024-04-30T15:00:00Z")
                .add("userAgent", "Sample useragent")
                .add("ipAddress", "192.168.1.1")
                .build();

        Response postResponse = target.path("/analytics")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(request));

        assertEquals(201, postResponse.getStatus(), "Insert should return 201");

        Response getResponse = target.path("/analytics")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        assertEquals(200, getResponse.getStatus(), "Fetch should return 200");

        String json = getResponse.readEntity(String.class);
        assertTrue(json.contains("12345"), "Returned analytics should include inserted linkId");
    }
}
