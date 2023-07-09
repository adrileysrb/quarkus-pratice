package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "nova")
public class Nova extends PanacheEntity {

    @Column(name = "name", length = 50, nullable = false)
    public String name;

    @Column(name = "bio", length = 3000, nullable = false)
    public String bio;
}