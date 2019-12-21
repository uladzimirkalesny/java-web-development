package by.epam.training.ws.provider.mapper;

import by.epam.training.ws.exception.UserNotAvailableException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserIdNotAvailableExceptionMapper implements ExceptionMapper<UserNotAvailableException> {
    @Override
    public Response toResponse(UserNotAvailableException e) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                       .entity(Response.Status.NOT_FOUND.getStatusCode() + "\nUser with id # " + e.getMessage() + " not available. Please correct id.")
                       .type(MediaType.TEXT_PLAIN_TYPE)
                       .build();
    }
}
