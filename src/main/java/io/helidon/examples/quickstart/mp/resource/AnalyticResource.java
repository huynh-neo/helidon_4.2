package io.helidon.examples.quickstart.mp.resource;

import java.util.List;

import io.helidon.examples.quickstart.mp.model.ClickAnalytics;
import io.helidon.examples.quickstart.mp.repository.ClickAnalyticsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/analytics")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnalyticResource {
    
    @Inject
    private ClickAnalyticsRepository clickAnalyticsRepository;

    @POST
    public Response insertClickAnalytics(@Valid ClickAnalytics analytics) {
        try {
            clickAnalyticsRepository.insert(analytics);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to insert analytics: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public List<ClickAnalytics> getAllClickAnalytics() {
        return clickAnalyticsRepository.findAll();
    }
}
