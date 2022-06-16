package com.tui.proof.service;

import com.tui.proof.dto.ClientDto;
import com.tui.proof.dto.OrderAddressDto;
import com.tui.proof.dto.OrderDto;
import com.tui.proof.dto.TuiOrderDto;
import com.tui.proof.model.Address;
import com.tui.proof.model.Client;
import com.tui.proof.model.Order;
import com.tui.proof.repository.AddressRepository;
import com.tui.proof.repository.ClientRepository;
import com.tui.proof.repository.OrderRepository;
import com.tui.proof.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class TuiOrderService {

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final NotificationService notificationService;

    /**
     * @param addressRepository
     * @param clientRepository
     * @param orderRepository
     * @param notificationService
     */
    @Autowired
    public TuiOrderService(AddressRepository addressRepository, ClientRepository clientRepository, OrderRepository orderRepository, NotificationService notificationService) {
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.notificationService = notificationService;
    }

    /**
     * @param tuiOrderDto
     */
    @Transactional
    public void save(TuiOrderDto tuiOrderDto) {
        Client client = clientRepository.findClientByEmail(tuiOrderDto.getClientDto().getEmail());
        Client clientToSave = clientRepository.save(buildClient(client, tuiOrderDto.getClientDto()));
        Address address = addressRepository.save(buildAddress(tuiOrderDto.getOrderAddressDto()));
        orderRepository.save(buildOrder(tuiOrderDto.getOrderDto(), clientToSave, address));
        notificationService.sendNotification();
    }

    /**
     * @param orderAddressDto
     * @return
     */
    private Address buildAddress(OrderAddressDto orderAddressDto) {
        return Address.builder()
                .city(orderAddressDto.getCity())
                .country(orderAddressDto.getCountry())
                .postcode(orderAddressDto.getPostcode())
                .street(orderAddressDto.getStreet())
                .build();
    }

    /**
     * @param orderDto
     * @param client
     * @param address
     * @return
     */
    private Order buildOrder(OrderDto orderDto, Client client, Address address) {
        return Order.builder()
                .orderTotal(Double.valueOf(orderDto.getPilotes()))
                .client(client)
                .address(address)
                .build();
    }

    /**
     * @param client
     * @param clientDto
     * @return
     */
    private Client buildClient(Client client, ClientDto clientDto) {
        return client == null ? Client.builder()
                                        .email(clientDto.getEmail())
                                        .firstName(clientDto.getName())
                                        .lastName(clientDto.getSurname())
                                        .telephone(clientDto.getPhoneNumber())
                                        .build() : updateClient(client, clientDto);
    }

    /**
     * @param client
     * @param clientDto
     * @return
     */
    private Client updateClient(Client client, ClientDto clientDto) {
        Optional.ofNullable(clientDto.getName()).ifPresent(client::setFirstName);
        Optional.ofNullable(clientDto.getSurname()).ifPresent(client::setLastName);
        Optional.ofNullable(clientDto.getPhoneNumber()).ifPresent(client::setTelephone);
        Optional.ofNullable(clientDto.getEmail()).ifPresent(client::setEmail);
        return client;
    }

}
