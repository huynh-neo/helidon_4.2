package io.helidon.examples.quickstart.mp.resource;

import io.helidon.microprofile.testing.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@HelidonTest
class LinkResourceTest extends AbstractContainerTest {

    @Inject
    private WebTarget target;

    @Test
    void testGetDynamicLinks() {
        Response response = target
                .path("/links")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        assertEquals(200, response.getStatus());
        JsonArray result = response.readEntity(JsonArray.class);
        System.out.println(result);
    }

    @Test
    void testCreateDynamicLink() {
        String json = """
                    {
                        "url": "https://example.com",
                        "description": "Example link"
                    }
                """;

        Response response = target
                .path("/links")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(json));

        assertEquals(200, response.getStatus());
    }
}
