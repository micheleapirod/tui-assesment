package com.tui.proof

import com.tui.proof.TuiCommonData
import com.tui.proof.dto.TuiOrderDto
import com.tui.proof.model.Address
import com.tui.proof.model.Client
import com.tui.proof.model.Order
import com.tui.proof.repository.AddressRepository
import com.tui.proof.repository.ClientRepository
import com.tui.proof.repository.OrderRepository
import com.tui.proof.service.NotificationService
import com.tui.proof.service.TuiOrderService
import spock.lang.Specification

class TuiSaveServiceSpecTest extends Specification {
    
    def addressRepository = Mock(AddressRepository)
    def clientRepository = Mock(ClientRepository)
    def orderRepository = Mock(OrderRepository)
    def notificationService = Mock(NotificationService)
    def tuiOrderService = new TuiOrderService(addressRepository, clientRepository, orderRepository, notificationService)
    
    def "save my new tui order dto"() {
        given:
            TuiOrderDto tuiOrderDto = TuiCommonData.buildOrderDtoTest()
        when:
            def response = tuiOrderService.save(tuiOrderDto)
        then:
            1 * clientRepository.findClientByEmail(_ as String) >> value ;
            callNumber * clientRepository.save(_ as Client)
            1 * addressRepository.save(_ as Address ) >> Address.builder().build()
            1 * orderRepository.save(_ as Order)
            1 * notificationService.sendNotification()
            response == null
        where:
             value                     | callNumber
             null                      | 0
             Client.builder().build()  | 1
    }
    
    
    
    

    
}
