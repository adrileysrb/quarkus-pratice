package org.acme.rest.service.impl;

import java.util.List;

import org.acme.persistence.model.Client;
import org.acme.persistence.repository.ClientRepository;
import org.acme.rest.service.ClientService;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClientServiceImpl implements ClientService{

    @Inject
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
      return this.clientRepository.findAll().list();
    }

    @Override
    public List<Client> find(String query, String sort, Integer pageIndex, Integer pageSize) {
        return this.clientRepository.find(query, Sort.by(sort)).page(pageIndex, pageSize).list();
    }

    @Override
    public Client findById(long id) {
        return this.clientRepository.findById(id);
    }

    @Override
    public void save(Client client) {
        this.clientRepository.persist(client);
    }

    @Override
    public void update(Client client) {
        Client c = this.findById(client.getId());
        c.setCity(client.getCity());
        c.setEmail(client.getEmail());
        c.setGender(client.getGender());
        c.setCountry(client.getCountry());
        c.setFirstName(client.getFirstName());
        c.setPhone(client.getPhone());
        c.setPostalCode(client.getPostalCode());
        c.setLastName(client.getLastName());
        c.setStreetName(client.getStreetName());

        this.clientRepository.persist(c);
    }

    @Override
    public void deleteById(long id) {
        this.clientRepository.deleteById(id);
    }
    
}
