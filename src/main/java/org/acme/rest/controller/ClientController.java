package org.acme.rest.controller;

import org.acme.rest.service.ClientService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientController {
    @Inject
    private ClientService clientService;

    @GET
    public Response findAll(){
        return Response.ok(this.clientService.findAll()).build();
    }

    @GET()
    @Path("/pagination")
    public Response find(
            @QueryParam("query") String query,
            @QueryParam("sort") @DefaultValue("id") String sort,
            @QueryParam("pageIndex") @DefaultValue("0") Integer pageIndex,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize

    ){
        return Response.ok(clientService.find(query, sort, pageIndex, pageSize)).build();
    }
}
