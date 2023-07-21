package org.acme.client;

import io.opentracing.Tracer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acme.exception.ServiceException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Objects;

@Path("/clients")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Tag(name = "client", description = "Client Operations")
@AllArgsConstructor
public class ClientResource {

    @Inject
    private ClientService clientService;

    @Inject
    private Tracer trace;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Get All Clients",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = Client.class)
            )
    )
    @Operation(summary = "Get All Clients",
            description = "Get All Clients.")
    public Response get() {
        trace.activeSpan().setTag("Tag_Operation", "Entry in method get()");
        return Response.ok(clientService.findAll()).build();
    }

    @GET
    @Path("/{clientId}")
    @APIResponse(
            responseCode = "200",
            description = "Get Client by clientId",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Client.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Client does not exist for clientId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Operation(summary = "Get Client",
            description = "Get by Clients Id.")
    public Response getById(@Parameter(name = "clientId", required = true) @PathParam("clientId") Short clientId) {
        trace.activeSpan().setTag("Tag_Operation", "Entry in method getById()");
        return clientService.findById(clientId)
                .map(client -> Response.ok(client).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @APIResponse(
            responseCode = "201",
            description = "Client Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Client.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Client",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Client already exists for clientId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Operation(summary = "Create Client",
            description = "Create Client.")
    public Response post(@NotNull @Valid Client client, @Context UriInfo uriInfo) {
        trace.activeSpan().setTag("Tag_Operation", "Entry in method post()");
        clientService.save(client);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(client.getClientId())).build();
        return Response.created(uri).entity(client).build();
    }

    @PUT
    @Path("/{clientId}")
    @APIResponse(
            responseCode = "204",
            description = "Client updated",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Client.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Client",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Client object does not have clientId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Path variable clientId does not match client.clientId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "No Client found for clientId provided",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Operation(summary = "Update Client",
            description = "Update Client.")
    public Response put(@Parameter(name = "clientId", required = true) @PathParam("clientId") Short clientId, @NotNull @Valid Client client) {
        trace.activeSpan().setTag("Tag_Operation", "Entry in method put()");
        if (!Objects.equals(clientId, client.getClientId())) {
            throw new ServiceException("Path variable clientId does not match client.clientId");
        }
        clientService.update(client);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}