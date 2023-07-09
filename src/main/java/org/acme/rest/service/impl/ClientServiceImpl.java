package org.acme.rest.service.impl;

import java.util.List;

import org.acme.persistence.model.Client;
import org.acme.rest.service.ClientService;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientServiceImpl implements ClientService{

    @Override
    public List<Client> findAll() {
      return null;
    }

    @Override
    public List<Client> findById(String query, String sort, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public Client findById(short id) {
        return null;
    }

    @Override
    public Client save(Client artist) {
        return null;
    }

    @Override
    public Client update(Client artist) {
        return null;
    }

    @Override
    public void deleteById(long id) {
    }
    
}
