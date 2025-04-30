package io.helidon.examples.quickstart.mp.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.time.Instant;
import java.util.List;

import io.helidon.examples.quickstart.mp.model.ClickAnalytics;
import io.helidon.examples.quickstart.mp.model.DynamicLink;
import io.helidon.examples.quickstart.mp.repository.ClickAnalyticsRepository;
import io.helidon.examples.quickstart.mp.repository.DynamicLinkRepository;

@Path("/links")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LinkResource {

    @Inject
    private DynamicLinkRepository linkRepo;

    @Inject
    private ClickAnalyticsRepository analyticsRepo;

    @POST
    public DynamicLink createLink(DynamicLink link) {
        return linkRepo.create(link);
    }

    @GET
    public List<DynamicLink> getLinks() {
        return linkRepo.findAll();
    }

    // @POST
    // @Path("/{id}/click")
    // public void logClick(@PathParam("id") Long id, @HeaderParam("User-Agent") String userAgent) {
    //     ClickAnalytics analytics = new ClickAnalytics(id, Instant.now(), userAgent);
    //     analyticsRepo.insert(analytics);
    // }

    // @GET
    // @Path("/analytics")
    // public List<ClickAnalytics> getAnalytics() {
    //     return analyticsRepo.findAll();
    // }
}
