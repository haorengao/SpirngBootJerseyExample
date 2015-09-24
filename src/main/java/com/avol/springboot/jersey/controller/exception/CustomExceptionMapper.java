package com.avol.springboot.jersey.controller.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Durga on 9/24/2015.
 */
@Provider
public class CustomExceptionMapper implements ExceptionMapper<Throwable> {

    public CustomExceptionMapper() {
    }

    @Override
    public Response toResponse(Throwable exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
    }
}
