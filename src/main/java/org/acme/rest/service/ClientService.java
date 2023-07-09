package org.acme.rest.service;

import java.util.List;

import org.acme.persistence.model.Client;

public interface ClientService {
    public List<Client> findAll();
    public List<Client> findById(String query, String sort, Integer pageIndex, Integer pageSize);
    public Client findById(final short id);
    public Client save(final Client artist);
    public Client update(final Client artist);
    public void deleteById(final long id);
}
