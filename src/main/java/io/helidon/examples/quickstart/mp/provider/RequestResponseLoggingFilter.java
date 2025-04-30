package io.helidon.examples.quickstart.mp.provider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER)
@ApplicationScoped
public class RequestResponseLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.hasEntity()) {
            InputStream originalInputStream = requestContext.getEntityStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            originalInputStream.transferTo(baos);
            byte[] bodyBytes = baos.toByteArray();
            String body = new String(bodyBytes, StandardCharsets.UTF_8);

            LOGGER.debug("Request: {} {}\nHeaders: {}\nBody: {}",
                    requestContext.getMethod(),
                    requestContext.getUriInfo().getRequestUri(),
                    requestContext.getHeaders(),
                    body);

            requestContext.setEntityStream(new ByteArrayInputStream(bodyBytes)); // reset stream
        } else {
            LOGGER.debug("Request: {} {}\nHeaders: {}",
                    requestContext.getMethod(),
                    requestContext.getUriInfo().getRequestUri(),
                    requestContext.getHeaders());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        Object entity = responseContext.getEntity();
        String json = "";
        try {
            if (entity != null) {
                json = MAPPER.writeValueAsString(entity);
            }
        } catch (Exception e) {
            LOGGER.warn("Failed to serialize response entity", e);
        }

        LOGGER.debug("Response Status: {}\nHeaders: {}\nBody: {}",
                responseContext.getStatus(),
                responseContext.getHeaders(),
                json);
    }
}

