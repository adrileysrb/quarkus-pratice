package org.acme.rest.resource;

import io.opentracing.Tracer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.acme.persistence.model.Client;
import org.acme.rest.service.ClientService;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/api/clients")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@RequestScoped
@Tag(name = "Clientes", description = "Endpoints para provimento de Clientes")
public class ClientResource {
    @Inject
    private ClientService clientService;

    @Inject
    Tracer trace;

    private static final Logger LOG = Logger.getLogger(ClientResource.class);

    @GET
    @Operation(summary = "Listar Clientes",
            description = "Listar todos os clientes cadastrados.")
    public Response findAll(){
        if (trace != null && trace.activeSpan() != null) {
            trace.activeSpan().setTag("Tag_Operação", "Entrada no método proverOperação");
        }
        LOG.info("Listar todos os clientes");
        List<Client> clients = this.clientService.findAll();
        LOG.info("Encontrado " + clients.size() + " na base de dados");
        return Response.ok(clients).build();
    }

    @GET()
    @Path("/pagination")
    @Operation(summary = "Listar Clientes - Pagination",
            description = "Listar todos os clientes cadastrados utilizando paginação.")
    public Response find(
            @QueryParam("query") String query,
            @QueryParam("sort") @DefaultValue("id") String sort,
            @QueryParam("pageIndex") @DefaultValue("0") Integer pageIndex,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize

    ){
        LOG.info("Listar clientes utilizando pagination");
        List<Client> clients = this.clientService.find(query, sort, pageIndex, pageSize);
        LOG.info("Retorno de " + clients.size() + " cliente(s) da base de dados");
        return Response.ok(clients).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar Cliente",
            description = "Consultar cliente pelo Id.")
    public Response findById(@PathParam("id") long id){
        LOG.info("Consultar cliente pelo ID");
        Client client = this.clientService.findById(id);
        if(client == null){
            LOG.info("Cliente nao encontrado na base de dados");
        }
        else {
            LOG.info("Cliente encontrado na base de dados");
        }
        return Response.ok(client).build();
    }

    @PUT
    @Operation(summary = "Editar Cliente",
            description = "Editar cliente cadastrado.")
    public void update(Client client){
        LOG.info("Entrada no metodo Editar cliente");
        this.clientService.update(client);
    }

    @POST
    @Operation(summary = "Inserir Cliente",
            description = "Inserir cliente.")
    public void persist(Client client){
        LOG.info("Entrada no metodo Inserir cliente");
        this.clientService.save(client);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @Operation(summary = "Apagar Cliente",
            description = "Apagar cliente cadastrado.")
    public Response delete(@PathParam("id") Long id){
        LOG.info("Deletar cliente pelo ID");
        this.clientService.deleteById(id);

        return Response.status(400).build();
    }

    public String teste(){
        return "Hello world";
    }
}
