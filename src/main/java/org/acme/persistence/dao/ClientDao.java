package org.acme.persistence.dao;

import org.acme.persistence.model.Client;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientDao implements PanacheRepository<Client>{
    
}
