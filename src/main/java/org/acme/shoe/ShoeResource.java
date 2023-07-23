package org.acme.shoe;

import io.opentracing.Tracer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
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

@Path("/shoes")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Tag(name = "shoeDTO", description = "ShoeDTO Operations")
@AllArgsConstructor
public class ShoeResource {
    @Inject
    private ShoeService ShoeService;

    @Inject
    private Tracer trace;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Get All Shoes",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = ShoeDTO.class)
            )
    )
    @Operation(summary = "Get All Shoes",
            description = "Get All Shoes.")
    public Response get() {
        trace.activeSpan().setTag("Tag_Operation", "Entry in method get()");
        return Response.ok(ShoeService.findAll()).build();
    }

    @GET
    @Path("/{shoeId}")
    @APIResponse(
            responseCode = "200",
            description = "Get Shoe by ShoeId",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ShoeDTO.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Shoe does not exist for ShoeId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Operation(summary = "Get Shoe",
            description = "Get by Shoes Id.")
    public Response getById(@Parameter(name = "shoeId", required = true) @PathParam("shoeId") Short shoeId) {
        trace.activeSpan().setTag("Tag_Operation", "Entry in method getById()");
        return ShoeService.findById(shoeId)
                .map(shoeDTO -> Response.ok(shoeDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @APIResponse(
            responseCode = "201",
            description = "Shoe Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ShoeDTO.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Shoe",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Shoe already exists for ShoeId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Operation(summary = "Create Shoe",
            description = "Create Shoe.")
    public Response post(@NotNull @Valid ShoeDTO shoeDTO, @Context UriInfo uriInfo) {
        trace.activeSpan().setTag("Tag_Operation", "Entry in method post()");
        ShoeService.save(shoeDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(shoeDTO.getShoeId())).build();
        return Response.created(uri).entity(shoeDTO).build();
    }

    @PUT
    @Path("/{shoeId}")
    @APIResponse(
            responseCode = "204",
            description = "Shoe updated",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ShoeDTO.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Shoe",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Shoe object does not have ShoeId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Path variable ShoeId does not match Shoe.ShoeId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "No Shoe found for ShoeId provided",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Operation(summary = "Update Shoe",
            description = "Update Shoe.")
    public Response put(@Parameter(name = "ShoeId", required = true) @PathParam("shoeId") Short shoeId, @NotNull @Valid ShoeDTO shoeDTO) {
        trace.activeSpan().setTag("Tag_Operation", "Entry in method put()");
        if (!Objects.equals(shoeId, shoeDTO.getShoeId())) {
            throw new ServiceException("Path variable ShoeId does not match Shoe.ShoeId");
        }
        ShoeService.update(shoeDTO);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
