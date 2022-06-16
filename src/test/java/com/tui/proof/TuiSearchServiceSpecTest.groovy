package com.tui.proof

import com.tui.proof.dto.TuiOrderDto
import com.tui.proof.model.Address
import com.tui.proof.model.Client
import com.tui.proof.model.Order
import com.tui.proof.repository.ClientRepository
import com.tui.proof.repository.OrderRepository
import com.tui.proof.search.SearchQ
import com.tui.proof.search.SearchSpecification
import com.tui.proof.service.SearchService
import spock.lang.Specification

class TuiSearchServiceSpecTest extends Specification{
    
    def clientRepository = Mock(ClientRepository)
    def orderRepository = Mock(OrderRepository)
    def tuiSearchService = new SearchService(clientRepository, orderRepository)
    
    def "Search service test"() {
        given:
            SearchQ searchQ = ["key": "firstName", "value": "George" ]
            Client client = Client.builder()
                    .firstName("Michele")
                    .email("michele@michele.com")
                    .lastName("waikiki")
                    .telephone("+44123456789")
                    .build()
        when:
            List<TuiOrderDto> orderDtoList = tuiSearchService.search(searchQ)
        then:
           1 * clientRepository.findAll(_ as SearchSpecification) >> [ client ]
           1 * orderRepository.findAllById(_ as List<Long>) >> [Order.builder()
                                                                        .address(Address.builder()
                                                                                        .street("via biscia")
                                                                                        .postcode("35030")
                                                                                        .city("Milano")
                                                                                        .country("Lombardia")
                                                                                        .build())
                                                                        .client(client)
                                                                        .orderTotal(20)
                                                                        .build()]
           !orderDtoList.isEmpty()
    }
    
}
