package com.tui.proof.domain;

import com.tui.proof.dto.TuiOrderDto;

import java.util.HashMap;
import java.util.Map;

public class PendingOrders {

    public static Map<String, TuiOrderDto> orderDtoMap = new HashMap<>();

    public static Map<String, TuiOrderDto> ordersInPending() {
        return orderDtoMap;
    }

}
