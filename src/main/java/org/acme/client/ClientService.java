package org.acme.client;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.acme.exception.ServiceException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public List<Client> findAll() {
        return this.clientMapper.toDomainList(clientRepository.findAll().list());
    }

    public Optional<Client> findById(@NonNull Short clientId) {
        return clientRepository.findByIdOptional(clientId)
                .map(clientMapper::toDomain);
    }

    @Transactional
    public void save(@Valid Client client) {
       // log.debug("Saving Client: {}", client);
        ClientEntity entity = clientMapper.toEntity(client);
        clientRepository.persist(entity);
        clientMapper.updateDomainFromEntity(entity, client);
    }

    @Transactional
    public void update(@Valid Client client) {
        //log.debug("Updating Client: {}", client);
        if (Objects.isNull(client.getClientId())) {
            throw new ServiceException("Client does not have a ClientId");
        }
        ClientEntity entity = clientRepository.findByIdOptional(client.getClientId())
                .orElseThrow(() -> new ServiceException("No Client found for ClientId[%s]", client.getClientId()));
        clientMapper.updateEntityFromDomain(client, entity);
        clientRepository.persist(entity);
        clientMapper.updateDomainFromEntity(entity, client);
    }

}
