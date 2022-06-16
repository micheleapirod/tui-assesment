package com.tui.proof.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tui.proof.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private String pilotes;

    public static OrderDto buildOrderDto(Order order) {
        return OrderDto.builder()
                .pilotes(String.valueOf(order.getOrderTotal()))
                .build();
    }

}
