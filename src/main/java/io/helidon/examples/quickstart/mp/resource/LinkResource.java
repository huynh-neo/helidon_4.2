package io.helidon.examples.quickstart.mp.resource;

import java.util.List;

import io.helidon.examples.quickstart.mp.model.DynamicLink;
import io.helidon.examples.quickstart.mp.repository.DynamicLinkRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/links")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LinkResource {

    @Inject
    private DynamicLinkRepository linkRepo;

    @POST
    public DynamicLink createLink(DynamicLink link) {
        return linkRepo.create(link);
    }

    @GET
    public List<DynamicLink> getLinks() {
        return linkRepo.findAll();
    }
}
