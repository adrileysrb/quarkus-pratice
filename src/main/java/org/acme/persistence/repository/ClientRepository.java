package org.acme.persistence.repository;

import org.acme.persistence.model.Client;
import org.eclipse.microprofile.opentracing.Traced;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@Traced
@ApplicationScoped
public class ClientRepository implements PanacheRepository<Client>{
    
}
