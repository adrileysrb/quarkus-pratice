package org.acme.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientRequest {
    
    private Short id;
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
