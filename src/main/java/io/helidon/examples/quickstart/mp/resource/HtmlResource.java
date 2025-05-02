package io.helidon.examples.quickstart.mp.resource;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/html")
@RequestScoped
public class HtmlResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtmlPage() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Sample HTML</title>
            </head>
            <body>
                <h1>Hello from Helidon!</h1>
                <p>This is an HTML response served by Helidon MP.</p>
            </body>
            </html>
        """;
    }
}