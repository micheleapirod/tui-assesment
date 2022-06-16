package com.tui.proof.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tui.proof.domain.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TuiOrderDto {

    private ClientDto clientDto;
    private OrderDto orderDto;
    private OrderAddressDto orderAddressDto;
    private String uuid;
    private String message;
    private OrderState orderState;
    private List<OrderDto> orders;

}
