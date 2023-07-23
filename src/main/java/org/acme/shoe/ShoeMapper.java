package org.acme.shoe;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "cdi")
public interface ShoeMapper {

    List<ShoeDTO> toDomainList(List<ShoeEntity> entities);

    ShoeDTO toDomain(ShoeEntity entity);

    @InheritInverseConfiguration(name = "toDomain")
    ShoeEntity toEntity(ShoeDTO domain);

    void updateEntityFromDomain(ShoeDTO domain, @MappingTarget ShoeEntity entity);

    void updateDomainFromEntity(ShoeEntity entity, @MappingTarget ShoeDTO domain);
}
