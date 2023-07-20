package org.acme.rest.controller;

import jakarta.ws.rs.;
import org.acme.rest.service.ClientService;

import jakarta.inject.Inject;
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

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") long id){
        return Response.ok(this.clientService.findById(id)).build();
    }

    @PUT
    public Response update(){
        return Response.ok(this.clientService.findAll()).build();
    }

    @POST
    public Response persist(){
        return Response.ok(this.clientService.findAll()).build();
    }

    @DELETE
    public Response delete(){
        return Response.ok(this.clientService.findAll()).build();
    }
}
