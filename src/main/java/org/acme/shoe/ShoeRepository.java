package org.acme.shoe;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShoeRepository implements PanacheRepositoryBase<ShoeEntity, Short> {
}
