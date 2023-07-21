package org.acme.client;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Client {

    @NotEmpty
    private Short clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String streetName;
    private String country;
    private String city;
    private String postalCode;
    private String phone;

}
