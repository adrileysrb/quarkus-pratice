package org.acme.shoe;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.io.Serializable;


@Entity(name = "Shoe")
@Table(name ="shoe")
@Data
public class ShoeEntity implements Serializable {

    @Id
    @NotEmpty
    @Column(name = "shoe_id", nullable=false)
    private Short shoeId;

    @Column(name = "line", nullable=false)
    private String line;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "price", nullable = false)
    private double price;

    public void delete() {
    }
}
