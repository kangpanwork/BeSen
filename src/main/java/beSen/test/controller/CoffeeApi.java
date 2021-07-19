package beSen.test.controller;

import beSen.spring.model.Coffee;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author 康盼Java开发工程师
 */
@Path("/coffee")
public class CoffeeApi {

    @POST
    @Path("/testApi")
    public Response getUserBId(@Valid Coffee coffee) throws RuntimeException {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("test error").build();
    }

}
