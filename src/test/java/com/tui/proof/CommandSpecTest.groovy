package com.tui.proof

import com.tui.proof.TuiCommonData
import com.tui.proof.command.OrderCommand
import com.tui.proof.dto.TuiOrderDto
import com.tui.proof.service.TuiOrderService
import org.junit.Assert
import spock.lang.Specification

class CommandSpecTest extends Specification{
    
    def tuiOrdeService = Mock(TuiOrderService)
    def commandTui = new OrderCommand(tuiOrdeService)

    def "order command save spec"() {
        given:
            TuiOrderDto tuiOrderDto = TuiCommonData.buildOrderDtoTest()
        when:
            TuiOrderDto response = commandTui.execute(TuiCommonData.buildOrderDtoTest())
        then:
            response != null
            response.getClientDto() != null
            response.getOrderAddressDto() != null
            response.getOrderDto() != null
            response.getOrderState() != null
    }


}
