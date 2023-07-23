package org.acme.shoe;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class ShoeDTO {
    @NotEmpty
    private Short shoeId;
    private String line;
    private String reference;
    private int size;
    private double price;

}
