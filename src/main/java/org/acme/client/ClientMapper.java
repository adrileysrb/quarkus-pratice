package org.acme.client;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ClientMapper {

    List<Client> toDomainList(List<ClientEntity> entities);

    Client toDomain(ClientEntity entity);

    @InheritInverseConfiguration(name = "toDomain")
    ClientEntity toEntity(Client domain);

    void updateEntityFromDomain(Client domain, @MappingTarget ClientEntity entity);

    void updateDomainFromEntity(ClientEntity entity, @MappingTarget Client domain);

}
