package com.tui.proof.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tui.proof.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderAddressDto {
    private String id;
    private String street;
    private String postcode;
    private String city;
    private String country;

    public static OrderAddressDto buildOrderAddressDto(Address address) {
        return OrderAddressDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .postcode(address.getPostcode())
                .country(address.getCountry())
                .build();
    }

}
