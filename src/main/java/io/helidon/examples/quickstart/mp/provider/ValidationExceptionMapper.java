package io.helidon.examples.quickstart.mp.provider;

import java.util.stream.Collectors;

import jakarta.annotation.Priority;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER) // Ensures this takes precedence over the default
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations()
                                  .stream()
                                  .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                                  .collect(Collectors.joining(", "));

        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("{\"error\": \"" + message + "\"}")
                       .type("application/json")
                       .build();
    }
}
