package com.tui.proof

import com.tui.proof.dto.ClientDto
import com.tui.proof.dto.OrderAddressDto
import com.tui.proof.dto.OrderDto
import com.tui.proof.dto.TuiOrderDto

class TuiCommonData {
    
    static TuiOrderDto buildOrderDtoTest() {
        return TuiOrderDto.builder()
                .orderDto(OrderDto.builder()
                        .pilotes("10")
                        .build()
                )
                .clientDto(ClientDto.builder()
                        .name("Mario")
                        .surname("Draghi")
                        .city("Milano")
                        .address("Via Mario Monti")
                        .zipCode("35030")
                        .phoneNumber("+39339345678")
                        .email("primeminister@mariodraghi.com")
                        .build()
                )
                .orderAddressDto(OrderAddressDto.builder()
                        .city("Roma")
                        .postcode("20")
                        .street("Via Palazzo Chigi")
                        .build())
                .build();
    }
}
