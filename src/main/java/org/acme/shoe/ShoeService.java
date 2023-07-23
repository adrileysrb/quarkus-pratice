package org.acme.shoe;

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
public class ShoeService {
    private final ShoeRepository shoeRepository;
    private final ShoeMapper shoeMapper;

    public List<ShoeDTO> findAll() {
        return this.shoeMapper.toDomainList(shoeRepository.findAll().list());
    }

    public Optional<ShoeDTO> findById(@NonNull Short shoeId) {
        return shoeRepository.findByIdOptional(shoeId)
                .map(shoeMapper::toDomain);
    }

    @Transactional
    public void save(@Valid ShoeDTO shoeDTO) {
        // log.debug("Saving ShoeDTO: {}", shoeDTO);
        ShoeEntity entity = shoeMapper.toEntity(shoeDTO);
        shoeRepository.persist(entity);
        shoeMapper.updateDomainFromEntity(entity, shoeDTO);
    }

    @Transactional
    public void update(@Valid ShoeDTO shoeDTO) {
        //log.debug("Updating ShoeDTO: {}", shoeDTO);
        if (Objects.isNull(shoeDTO.getShoeId())) {
            throw new ServiceException("ShoeDTO does not have a ShoeId");
        }
        ShoeEntity entity = shoeRepository.findByIdOptional(shoeDTO.getShoeId())
                .orElseThrow(() -> new ServiceException("No ShoeDTO found for ShoeId[%s]", shoeDTO.getShoeId()));
        shoeMapper.updateEntityFromDomain(shoeDTO, entity);
        shoeRepository.persist(entity);
        shoeMapper.updateDomainFromEntity(entity, shoeDTO);
    }

}
