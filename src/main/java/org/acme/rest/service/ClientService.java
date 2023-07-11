package org.acme.rest.service;

import java.util.List;

import org.acme.persistence.model.Client;

public interface ClientService {
    public List<Client> findAll();
    public List<Client> find(String query, String sort, Integer pageIndex, Integer pageSize);
    public Client findById(final long id);
    public Client save(final Client client);
    public Client update(final Client client);
    public void deleteById(final long id);
}
