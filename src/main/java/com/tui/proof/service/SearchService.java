package com.tui.proof.service;

import com.tui.proof.dto.ClientDto;
import com.tui.proof.dto.OrderAddressDto;
import com.tui.proof.dto.OrderDto;
import com.tui.proof.dto.TuiOrderDto;
import com.tui.proof.model.Client;
import com.tui.proof.model.Order;
import com.tui.proof.repository.ClientRepository;
import com.tui.proof.repository.OrderRepository;
import com.tui.proof.search.SearchQ;
import com.tui.proof.search.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    /**
     * @param clientRepository
     * @param orderRepository
     */
    @Autowired
    public SearchService(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * @param searchQ
     * @return
     */
    @Transactional(readOnly = true)
    public List<TuiOrderDto> search(SearchQ searchQ) {
        SearchSpecification searchSpecification = new SearchSpecification(searchQ);
        List<Client> clients = clientRepository.findAll(searchSpecification);
        List<Long> ordersId = CollectionUtils.isEmpty(clients) ? new ArrayList<>() : clients.stream().map(Client::getId).collect(Collectors.toList());
        List<Order> orders = orderRepository.findAllById(ordersId);
        return CollectionUtils.isEmpty(orders) ? new ArrayList<>() : orders.stream().map(this::buildTuiOrder).collect(Collectors.toList());
    }

    /**
     * @param order
     * @return
     */
    private TuiOrderDto buildTuiOrder(Order order) {
        return TuiOrderDto.builder()
                            .orderDto(OrderDto.buildOrderDto(order))
                            .clientDto(ClientDto.buildClientDto(order.getClient()))
                            .orderAddressDto(OrderAddressDto.buildOrderAddressDto(order.getAddress()))
                            .build();
    }

}
