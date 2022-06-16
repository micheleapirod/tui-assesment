package com.tui.proof.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tui.proof.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {

    private String name;
    private String surname;
    private String email;
    private String address;
    private String zipCode;
    private String city;
    private String phoneNumber;

    public static ClientDto buildClientDto(Client client) {
        return ClientDto.builder()
                .name(client.getFirstName())
                .surname(client.getLastName())
                .email(client.getEmail())
                .build();
    }
}
